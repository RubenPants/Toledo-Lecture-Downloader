
import java.util.HashMap;
import java.util.Map;

/**
 * The manager will be the main controller which delegates the following tasks:
 * 1) It uses the Proxy server to send GET messages and receive their response
 * 2) It will use the VideoManager to stitch the received video binaries back together
 */
public class Manager {

    private static HashMap<String, String> parseDic = new HashMap<String, String>() {{
        put("seg-1-", "seg-[i]-");
        put("seg-2-", "seg-[i]-");
        put("seg-3-", "seg-[i]-");
        put("seg-4-", "seg-[i]-");
        put("seg-5-", "seg-[i]-");
    }};

    /**
     * Run the program
     */
    static void useManager(Dialog dialog, String fileName, String inputUrl) throws Exception {
        // Parse the input url
        String segUrl = parseUrl(inputUrl);

        // Start the downloading process
        downloadVideo(segUrl, fileName, dialog);
    }

    /**
     * Split the input url on the split-character-set ([i])
     *
     * @param segUrl: The input url which needs to be split
     * @return The splitted input url
     */
    private static String[] splitUrl(String segUrl) throws Exception {
        if (segUrl.contains("[i]")) {
            segUrl = segUrl.replace("[i]", "replace_me");
            return segUrl.split("replace_me");
        } else {
            throw new Exception("Invalid input url: [i] was missing");
        }
    }

    /**
     * Download the video
     *
     * @param segUrl: The input url which needs to be downloaded
     */
    private static void downloadVideo(String segUrl, String fileName, Dialog dialog) throws Exception {
        // Split URL in two
        String[] splitUrl = splitUrl(segUrl);
        String first = splitUrl[0];
        String second = splitUrl[1];

        int index = 1;
        while (true) {
            try {
                String i = Integer.toString(index);
                Proxy.getHTML(first + i + second, fileName);

                dialog.setSegmentNr(i);
                System.out.println("Successfully downloaded segment " + i);

                index++;
            } catch (Exception e) {
                throw new Exception("Video stopped downloading");
            }
        }
    }

    /**
     * Parse the url to make it download-ready
     *
     * @param inputUrl: The unparsed url
     * @return A parsed url
     */
    private static String parseUrl(String inputUrl) throws Exception {
        for (Map.Entry<String, String> elem : parseDic.entrySet()) {
            if (inputUrl.contains(elem.getKey())) {
                return inputUrl.replaceAll(elem.getKey(), elem.getValue());
            }
        }

        throw new Exception("Could not parse given url");
    }
}
