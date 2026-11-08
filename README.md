Welcome to The Social Code backend repo.

I have multiple .md files so here is what they are for
1: README_SOURCES.md
  this is where you can find all my sources I used for the backend
2: UNDERSTANDING.md
  this is where I write how I use the sources in my backend




To use locally you must use [localhost:8080/api/v1] or to reach the live backend
you must use [tsc-db.yurphagames.net/api/v1]

You may also need a ffmpeg and ffprobe to be installed on your machine </br>
So I have 2 choices
- Option 1
  - 1 Download through Github using
    - git clone https://github.com/DasStudioINC/TheSocialCode-Backend.git
  - 2 then install the zip at
    - https://www.gyan.dev/ffmpeg/builds/
  - 3 And then configure the postService to locate the file properly
- Option 2
  - Go to https://db-downloader.yurphagames.net
  - This comes all prepared with the tools already in the project and configured




Endpoints: 


<h2>USER:</h2>

| Endpoint                                     | What it's for                                            |
|:---------------------------------------------|:---------------------------------------------------------|
| GET                                          |
| `/api/v1/users`                              | Returns All users                                        |
| `/api/v1/users/id/{id}`                      | Returns The user with that id                            |
| `/api/v1/users/handle/{handle} `             | Returns The user with that handle                        |
| `/api/v1/users/profile/get/handle/{handle} ` | Returns The profile picture of the user with that handle |
| POST                                         |
| `/api/v1/users`                              | Creates a new user using a json block                    |
| PUT                                          |
| `/api/v1/users/handle/{handle} `             | Updates The user with that handle                        |
| `/api/v1/users/profile/set/handle/{handle} ` | Updates The user with that handle to put a profile       |
| DELETE                                       |
| `/api/v1/users/handle/{handle} `             | Deletes The user with that handle                        |


<h2>POST:</h2>

| Endpoint                                                        | What it's for                                                |
|:----------------------------------------------------------------|:-------------------------------------------------------------|
| GET                                                             |
| `/api/v1/posts`                                                 | Returns All posts                                            |
| `/api/v1/posts/filter?userhandle=&postname=&index=  `           | Returns The posts or post data with the information inserted |
| `/api/v1/posts/search/{searchTitle}/post `                      | Returns The post file with that search Title                 |
| POST                                                            |
| `/api/v1/posts/upload?file=&userhandle=&postname=&description=` | Creates a new post                                           |
| PUT                                                             |
| `/api/v1/posts/update/searchTitle/{searchTitle}/post `          | Updates The post with that searchTitle                       |
| DELETE                                                          |
| `/api/v1/posts/delete?searchTitle= `                            | Deletes The post with that searchTitle                       |



<h2>INTERACTIONS:</h2>

| Endpoint                                                        | What it's for                                                                 |
|:----------------------------------------------------------------|:------------------------------------------------------------------------------|
| GET                                                             |
| `/api/v1/interactions`                                          | Returns All interactions                                                      |
| `/api/v1/interactions/filter/comments?searchTitle  `            | Returns The comments for that post with the searchTitle                       |
| POST                                                            |
| `/api/v1/interactions/follow?iuh=&puh= `                        | Creates a new interaction of type follow to user with <br/> that user handle  |
| `/api/v1/interactions/like?iuh=&puh=&searchTitle=`              | Creates a new interaction of type like to post with <br/> that search title   |
| `/api/v1/interactions/comment?iuh=&puh=&searchTitle=&comment= ` | Creates a new interaction of type comment to post with<br/> that search title |

