package simon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class SynthController {
	private Synthesizer synth;
	private MidiChannel midiChannel;
	private int velocity;
	private int noteNumber;
	private boolean playing;

	public SynthController() {
		try {
			this.synth = MidiSystem.getSynthesizer();
			synth.open();
		} catch (MidiUnavailableException e) {
			throw new RuntimeException(e);
		}
		// There are 16 different channels. They could be used at the same time to mix
		// different sounds. For now, we will only play one sound at a time so we choose
		// the first channel.
		this.midiChannel = synth.getChannels()[0];
		// Velocity is volume. For now, we just use 80.
		this.velocity = 80;
		this.noteNumber = 60;
		this.playing = false;
		setInstrument(getInstruments().get(0).getName().trim());
	}

	private List<Instrument> getInstruments() {
		List<Instrument> instruments = Arrays.asList(synth.getAvailableInstruments());
		Collections.sort(instruments, new Comparator<Instrument>() {
			@Override
			public int compare(Instrument o1, Instrument o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return instruments;
	}

	public List<String> getInstrumentNames() {
		return getInstruments().stream().map(instrument -> instrument.getName().trim()).collect(Collectors.toList());
	}

	private Instrument getInstrumentByName(String name) {
		for (Instrument instrument : synth.getAvailableInstruments()) {
			if (instrument.getName().trim().equalsIgnoreCase(name)) {
				return instrument;
			}
		}
		return null;
	}

	public void setInstrument(String instrumentName) {
		Instrument instrument = getInstrumentByName(instrumentName);
		if (instrument != null) {
			if (playing) {
				stop();
			}
			midiChannel.programChange(instrument.getPatch().getProgram());
			if (playing) {
				start();
			}
		}
	}

	public void setNoteNumber(int noteNumber) {
		if (playing) {
			stop();
		}
		this.noteNumber = noteNumber;
		if (playing) {
			start();
		}
	}

	public void startPlayingNote() {
		if (!playing) {
			start();
			playing = true;
		}
	}

	public void stopPlayingNote() {
		if (playing) {
			stop();
			playing = false;
		}
	}

	public void close() {
		if (synth != null && synth.isOpen()) {
			synth.close();
		}
	}

	private void start() {
		midiChannel.noteOn(noteNumber, velocity);
	}

	private void stop() {
		midiChannel.noteOff(noteNumber);
	}
}
