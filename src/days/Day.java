package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Day {


    public String subdir;

    public abstract void execute();

    String currentPackagePath = Day.class.getPackageName();
    String localDir = System.getProperty("user.dir");

    protected String getCalibrationPath(){
        return localDir + "/src/" + currentPackagePath + "/" + subdir + "/input.txt";
    }

    public List<String> data = new ArrayList<>();

    public void readData(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getCalibrationPath()));
            String line = reader.readLine();

            while (line != null) {
                data.add(line);
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e);
            //handle exception
        }
    }
}
