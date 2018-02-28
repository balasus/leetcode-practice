package concurrency;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVReader {
    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        long start = System.currentTimeMillis();
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(processors);
        CompletionService<List<CSVLine>> pool = new ExecutorCompletionService<>(threadPool);

        Callable<List<CSVLine>> task = ()->{
            List<CSVLine> recordList = csvReader.readFileContent("D:\\bala\\12_preparation\\public\\practiceCoding\\" +
                    "projects\\leetCode\\projects\\com.practice\\concurrency\\bigfile.csv");
            return recordList;
        };

        try {
            Future<List<CSVLine>> submit = pool.submit(task);
            System.out.println(Thread.currentThread().getName() + "==>" + submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        long end = (System.currentTimeMillis()-start);

        System.out.println(end);
    }

    public List<CSVLine> readFileContent(String file){
        List<CSVLine> csvLines = new ArrayList<>();
        BufferedReader br = null;
        try{
            File inputF = new File(file);
            InputStream inputFS = new FileInputStream(inputF);
            br = new BufferedReader(new InputStreamReader(inputFS));
            csvLines = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvLines;
    }

    private class CSVLine{
        //title	uti	rcplei	nrcplei	desc
        String title;
        String uti;
        String rcplei;
        String nrcplei;
        String desc;

        CSVLine(String title, String uti, String rcplei, String nrcplei, String desc){
            this.title=title;
            this.uti=uti;
            this.rcplei=rcplei;
            this.nrcplei=nrcplei;
            this.desc=desc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CSVLine csvLine = (CSVLine) o;
            return Objects.equals(uti, csvLine.uti) &&
                    Objects.equals(rcplei, csvLine.rcplei) &&
                    Objects.equals(nrcplei, csvLine.nrcplei);
        }

        @Override
        public int hashCode() {
            return Objects.hash(uti, rcplei, nrcplei);
        }

        @Override
        public String toString() {
            return "CSVLine{" +
                    "title='" + title + '\'' +
                    ", uti='" + uti + '\'' +
                    ", rcplei='" + rcplei + '\'' +
                    ", nrcplei='" + nrcplei + '\'' +
                    ", desc='" + desc + '\'' +
                    '}'+"\n";
        }
    }

    private Function<String, CSVLine> mapToItem = line ->{
        String[] eachLine = line.split(",");
        CSVLine csvLine = new CSVLine(eachLine[0], eachLine[1], eachLine[2], eachLine[3], eachLine[4]);
        return csvLine;
    };
}
