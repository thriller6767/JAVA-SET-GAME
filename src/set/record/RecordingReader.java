package set.record;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

/**
 * Class that reads a Set recording file.
 */
public class RecordingReader {

    private BufferedReader reader;

    /**
     * Constructor for creating a new Recording Reader.
     *
     * @param fileName File name of recording file.
     * @throws FileNotFoundException If the recording file is not found.
     */
    public RecordingReader(String fileName) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(fileName));
    }

    /**
     * Reads the next command from the recording file.
     *
     * @return Next instruction.
     */
    public Optional<Instruction> nextInstruction() {
        String raw = null;

        try {
            raw = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (raw != null) {
            raw = raw.trim();

            String[] split = raw.split(" ", 2);
            if (split.length > 1) {
                return Optional.of(new Instruction(split[0], split[1]));
            } else {
                return Optional.of(new Instruction(split[0], ""));
            }
        } else {
            return Optional.empty();
        }


    }

}
