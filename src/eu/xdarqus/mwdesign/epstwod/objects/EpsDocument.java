package eu.xdarqus.mwdesign.epstwod.objects;

import java.io.*;
import java.util.Date;

public class EpsDocument {

    private String title;
    private BufferedWriter bufferedWriter;
    private String VERSION = "1.0.0";

    public EpsDocument(String title, OutputStream os) throws IOException {
        this.title = title;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
        write(bufferedWriter);
    }

    public synchronized void write(Writer writer) throws IOException {
        writer.write("%!PS-Adobe-3.0 EPSF-3.0\n");
        writer.write("%%Creator: Mateusz Madej v" + this.VERSION + ", matmadproject@gmail.com" + "\n");
        writer.write("%%Title: " + this.title + "\n");
        writer.write("%%CreationDate: " + new Date() + "\n");
        writer.write("%%BoundingBox: 0 0 " + ((int) Math.ceil(Float.POSITIVE_INFINITY + 0)) + " " + ((int) Math.ceil(Float.POSITIVE_INFINITY + 0)) + "\n");
        writer.write("%%EndComments\n");
        writer.write("%%BeginSetup\n");
        writer.write("%%EndSetup\n");
        writer.write("%%BeginProlog\n");
        writer.write("%%EndProlog\n");
        writer.flush();
    }

    public synchronized void flush() throws IOException {
        this.bufferedWriter.flush();
    }

    public synchronized void close() throws IOException {
        this.bufferedWriter.flush();
        this.bufferedWriter.close();
    }

    public synchronized void append(String line) {
        try {
            this.bufferedWriter.write("\n" + line + "\n");
        } catch (IOException e) {
            throw new EpsException("Could not write to the output file: " + e);
        }
    }
}
