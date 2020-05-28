# Toledo Lecture Downloader
## Download Lectures
To download your lecture you first need to do the following steps:
1. Open the lecture inside a new tab by clicking on the icon indicated
 in the figure below. <br/>
![new tab image](https://raw.githubusercontent.com/RubenPants/Toledo-Lecture-Downloader/master/img/new_tab.JPG)

2. Open *developer tools* in the new tab you just opened (the tab the
lecture is in). You can open *Developer tools* by going to your tab-
settings, or pressing F12 in chrome. <br/>
![developer tools](https://raw.githubusercontent.com/RubenPants/Toledo-Lecture-Downloader/master/img/dev_tools.png)

3. Once developer, go to the *network* section. After you did this, you can play the lecture video. Video-segments start to occur as highlighted in the image below. Don't mind the bloat of messages, only the *seg-* messages are relevant to download the lecture. If nothing happens, please refresh
the tab (press CTRL+R). <br/>
![network pane](https://raw.githubusercontent.com/RubenPants/Toledo-Lecture-Downloader/master/img/network.JPG)

4. Withing the *flood* of HTTP-requests, try to find a requests title
*seg-1-...* (seg-*2* till *5* will also do the trick). Click on this
request, a new pane will open. <br />
![segment request](https://raw.githubusercontent.com/RubenPants/Toledo-Lecture-Downloader/master/img/network_seg.JPG)

5. In the pane that just opened, a (quite large) *Request URL* can be
found, you have to copy this url. 
![segment request url](https://raw.githubusercontent.com/RubenPants/Toledo-Lecture-Downloader/master/img/copy_url.JPG)

6. Open the program by running the *ToledoVideoDownloader.jar* file (note that Java must be installed on your PC in order to run this) which can be found inside the root-directory on GitHub if you didn't yet downloaded it. Within the program, do the following:
   * Fill in a file name. After downloading, the video can be found
    under this name inside the same directory the program 
    (*ToledoVideoDownloader.jar*) is in.
   * Paste the *request URL* of step 5 inside the *URL of first
    segment* block.
   * Press the *Download* button to run the program.<br/>![program](https://raw.githubusercontent.com/RubenPants/Toledo-Lecture-Downloader/master/img/program.JPG)
7. The lecture will now start downloading. When finished, a message will pop-up. Please check the lecture-file if it's (almost) as long as the original lecture. If so, you're good to go! Note that the downloaded video will most likely be a `.ts` file, which is nothing to worry about.


## Q&A
#### I could not find the `seg-1-...` HTTP-request in the Network pane
First make sure you are getting HTTP-requests, it is always a safe bet to
refresh the page (press CTRL+R). If you still cannot find the `seg-1-...`
request, then try to search for another request that often reoccurs. The
needed HTTP-request will always contain an index specifying which segment
is requested, so search for this.


#### The download is incomplete
You must have a stable internet connection whilst downloading. If you start
the download but don't finish it (e.g. if you stop downloading after 40min 
from a 2hr lecture), then the downloading process will end and only the first
40min downloaded will be saved. You'll have to start the downloading process
all over again if you want the full lecture to be downloaded.


#### It said *Download finished* but there is no video file
It is possible that not all lectures use the same segment syntax (i.e. 
the *Request url* does not contain `seg-1-...`). It will always be possible 
to do the following:

1. You can manually change the url. Normally, the request-url will have some
kind of accumulating index (e.g. `lec-1-...` instead of `seg-1-...`), in this 
case, replace this index with the following: `[i]`. Normally the lecture 
would then be downloaded without a problem.

2. If you want to start the downloading from a given segment (e.g. when the 
previous download stopped due to a connection failure) then this is possible 
by adding `[x<number>]` instead of `[i]` with `<number>` the segment-index
you want to start downloading from. Note that if you already have a 
video-file with the name you have chosen, then the newly downloaded segments
will be simply added at the back of this segment.

3. If you plan to download multiple lectures it could be quite a lot of
work to manually change each url. It is possible to change the code 
(this *solution* is only recommended if you are familiar with coding).
Inside the *Manager.java* class a dictionary named *parseDic* is created.
Find the prefix used for your lectures and put this in the dictionary.
Say for example that your lecture uses the following: *lec-1-...* then
add the following to the dictionary: `put("lec-1-", "lec-[i]-");`.

#### Why is the download taking this long?
A lecture is downloaded by downloading segments of around 9 seconds
separately. These segments are then (sequentially) knitted back together. 
This process takes quite some overhead with it, but this is unfortunately
how online video-streaming services work. The downloading-speed mainly
depends on how stable your internet connection is. Note that even with a
solid internet connection it still can take up to 20 minutes to load a 2
hour lecture.

## Copy Right
KU Leuven (or any other platform for which you'll try to use this program
to download video's from) has full copy right. If you want to download the
video's then you must always ask for permission from the owner of the video.
<br/>
I, as the creator of this program, am not responsible for others which use 
this code in any malicious way possible and I definitely discourage anyone 
whom will try to do so.
