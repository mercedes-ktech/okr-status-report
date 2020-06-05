import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class OKRReader {

    public static class CSVEntry {
        public String objective;
        public String keyResult1;
        public String keyResult2;
        public String keyResult3;
        public String keyResult4;
        public String keyResult5;

        public void setObjective(String objective) {
            this.objective = objective;
        }

        public void setKeyResult1(String keyResult1) {
            this.keyResult1 = keyResult1;
        }

        public void setKeyResult2(String keyResult2) {
            this.keyResult2 = keyResult2;
        }

        public void setKeyResult3(String keyResult3) {
            this.keyResult3 = keyResult3;
        }

        public void setKeyResult4(String keyResult4) {
            this.keyResult4 = keyResult4;
        }

        public void setKeyResult5(String keyResult5) {
            this.keyResult5 = keyResult5;
        }
    }


    public static void readOKR() {

        Scanner scanner = new Scanner(System.in);

        try (Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\mercedes.tarrio\\Downloads\\OKR.csv"))) {
            String[] columns = {"objective", "keyResult1", "keyResult2", "keyResult3", "keyResult4", "keyResult5"};

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(CSVEntry.class);
            strategy.setColumnMapping(columns);

            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (CSVEntry okr : (Iterable<CSVEntry>) csvToBean) {
                Collection<KeyResult> newKeyResults = new ArrayList<>();
                RandomProgressGenerator randomProgressGenerator = new RandomProgressGenerator();

                //skip blank KR
                if (okr.keyResult1 != null && !okr.keyResult1.trim().isEmpty()) newKeyResults.add(new KeyResult(okr.keyResult1, randomProgressGenerator));
                if (okr.keyResult2 != null && !okr.keyResult2.trim().isEmpty()) newKeyResults.add(new KeyResult(okr.keyResult2, randomProgressGenerator));
                if(okr.keyResult3 != null && !okr.keyResult3.trim().isEmpty()) newKeyResults.add(new KeyResult(okr.keyResult3, randomProgressGenerator));
                if(okr.keyResult4 != null && !okr.keyResult4.trim().isEmpty()) newKeyResults.add(new KeyResult(okr.keyResult4, randomProgressGenerator));
                if(okr.keyResult5 != null && !okr.keyResult5.trim().isEmpty()) newKeyResults.add(new KeyResult(okr.keyResult5, randomProgressGenerator));


                OKR newOKR = new OKR(okr.objective, newKeyResults, randomProgressGenerator);
                System.out.println("Objective: " + okr.objective);
                System.out.println("Assign progress to objective? Y/N");
                String assignProgress = scanner.nextLine();

                if (assignProgress.equals("Y")) {
                    System.out.println("Good progress? true/false");

                    boolean goodProgress = Boolean.valueOf(scanner.nextLine());
                    newOKR.setGoodProgress(goodProgress);
                    System.out.println("OKR completion is: " + newOKR.calculateOKRCompletion() + "%");
                } else if (assignProgress.equals("N")) {
                    System.out.println("Assign progress to Key Results");

                    for (KeyResult keyResult : newKeyResults) {
                        System.out.println("Key Result: " + keyResult.getKeyResult());
                        System.out.println("Good progress? true/false");

                        boolean goodProgressKR = Boolean.valueOf(scanner.nextLine());
                        keyResult.setGoodProgress(goodProgressKR);

                        System.out.println("KR completion is: " + keyResult.getCompletion() + "%");
                    }

                    System.out.println("OKR completion is: " + newOKR.calculateOKRCompletion() + "%");
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
