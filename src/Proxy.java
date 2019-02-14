import java.io.*;
import java.net.*;

/**
 * This proxy is used to send HTTP GET-requests to retrieve the .ts files from server
 */
class Proxy {
    static void getHTML(String urlToRead, String fileName) throws Exception {
        URL u = new URL(urlToRead);
        URLConnection uc = u.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file.");
        }
        InputStream raw = uc.getInputStream();
        InputStream in = new BufferedInputStream(raw);
        byte[] data = new byte[contentLength];
        int bytesRead;
        int offset = 0;
        while (offset < contentLength) {
            bytesRead = in.read(data, offset, data.length - offset);
            if (bytesRead == -1)
                break;
            offset += bytesRead;
        }
        in.close();

        if (offset != contentLength) {
            throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
        }

        try {
            File file = new File("./" + fileName + ".ts");
            FileOutputStream out = new FileOutputStream(file, true);
            out.write(data);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new IOException("Download has ended");
        }
    }
}