<h1 style="color: #2b9dfb;">The Social Code Sources</h1>
<h5> &#x21da; Yurpha Games : Zack Da Silva &#x21db;</h5>

<h3 class="head">VIDEO COMPRESSING SOURCES:</h3>
1: https://stackoverflow.com/questions/50042483/mp4-video-compressor-in-java



<h3 class="head">FILE SIZE LIMIT SOURCES:</h3>
1: https://stackoverflow.com/questions/34177873/max-limit-of-multipartfile-in-spring-boot

<h3 class="head">STORAGE BUCKETS INTEGRATION SOURCES:</h3>

[Cloudflare] <br>
1: https://blog.boottechsolutions.com/2026/04/07/how-to-upload-files-to-cloudflare-r2-with-spring-boot-fast-scalable-guide/

[Backblaze] <br>
2: https://www.backblaze.com/docs/en/cloud-storage-get-started-with-a-backblaze-integration <br>
3: https://www.backblaze.com/docs/cloud-storage-use-java-to-create-an-application <br>
4: https://www.backblaze.com/apidocs/s3-list-objects-v2 <br>


<h3 class="head">HOW TO CREATE A SERVER</h3>
<p> winget install --id Cloudflare.cloudflared</p>
<p> cloudflared tunnel login</p>
<p> cloudflared tunnel create spring-db-tunnel</p>
<p> cloudflared tunnel route dns spring-db-tunnel [databasename].yourdomain.something</p>
<p> cloudflared tunnel run spring-db-tunnel</p>
<p> curl.exe -i https://[databasename].yourdomain.something/api/v1/sources</p>
<p class="head">
 This is where you can find all the sources I used <br>
 to build the back end
</p>
 

