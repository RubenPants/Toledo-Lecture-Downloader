# Toledo Lecture Downloader
## Download Lectures
To download your lecture you first need to do the following steps:
1. Open the lecture inside a new tab by clicking on the icon indicated
 in the figure below. Please do not start the lecture yet! <br/>
![alt text](/img/new_tab.jpg)

2. Open *developer tools* in the new tab you just opened (the tab the
lecture is in). *Developer tools* can be opened by going to your tab-
settings, or pressing F12 in chrome. <br/>
![alt text](/img/dev_tools.png)

3. Once developer options is opened you have to go to the *network*
section. After you did this, you can play the lecture video. HTTP-
request normally will start coming in (no worries, most of them
are irrelevant for our purpose). If nothing happens, please refresh
the tab (press CTRL+R). <br/>
![alt text](/img/network.jpg)

4. Withing the *flood* of HTTP-requests, try to find a requests title
*seg-1-...* (seg-*2* till *5* will also do the trick). Click on this
request, a new pane will open. <br />
![alt text](/img/network_seg.jpg)

5. In the pane that just opened, a (quite large) *Request URL* can be
found, you have to copy this url. 
![alt text](/img/copy_url.jpg)

6. Open the program by running the *ToledoVideoDownloader.jar* file
which can be found inside the root-directory on github if you didn't
yet downloaded it. Within the program, do the following:
    - Fill in a file name. After downloading, the video can be found
    under this name inside the same directory the program 
    (*ToledoVideoDownloader.jar*) is in.
    - Paste the *request url* of the step 5 inside the *URL of first
    segment* block.
    - Press the *Download* button to run the program.<br/>
![alt text](/img/program.jpg)


## Q&A
#### I could not find the seg-1-... HTTP-request in the Network pane
// TODO

#### I could not find the seg-1-... prefix
It is possible that not all lectures use the same segment syntax as
described above. In the case the *Request url* does not contain 
*seg-1-...* it will always be possible to do the following:

1. Manually change the url. Normally, the request-url will have some
kind of accumulating index (e.g. *part-1-...*), in this case, replace
this index with the following: *[i]*. Normally the lecture would then
be downloaded without a problem.

2. If you plan to download multiple lectures it could be quite a lot of
work to manually change each url. It is possible to change the code 
(this *solution* is only recommended if you are familiar with coding).
Inside the *Manager.java* class a dictionary named *parseDic* is created.
Find the prefix used for your lectures and put this in the dictionary.
Say for example that your lecture uses the following: *lec-1-...* then
add the following to the dictionary: ``` put("lec-1-", "lec-[i]-"); ```. 