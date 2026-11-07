Welcome to The Social Code backend repo.

I have multiple .md files so here is what they are for
1: README_SOURCES.md
  this is where you can find all my sources I used for the backend
2: UNDERSTANDING.md
  this is where I write how I use the sources in my backend




To use locally you must use [localhost:8080/api/v1] or to reach the live backend
you must use [tsc-db.yurphagames.net/api/v1]

You must also install the required tool: https://www.gyan.dev/ffmpeg/builds/


Endpoints: 


USER:

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


POST:

| Endpoint                                                        | What it's for                                                |
|:----------------------------------------------------------------|:-------------------------------------------------------------|
| GET                                                             |
| `/api/v1/posts`                                                 | Returns All posts                                            |
| `/api/v1/posts/filter?userhandle=&postname=&index=  `           | Returns The posts or post data with the information inserted |
| `/api/v1/posts/search/{searchTitle}/post `                      | Returns The post file with that search Title                 |
| POST                                                            |
| `/api/v1/posts/upload?file=&userhandle=&password=&description=` | Creates a new post                                           |
| PUT                                                             |
| `/api/v1/posts/update/searchTitle/{searchTitle}/post `          | Updates The post with that searchTitle                       |
| DELETE                                                          |
| `/api/v1/posts/delete?searchTitle= `                            | Deletes The post with that searchTitle                       |



INTERACTIONS:

| Endpoint                                                        | What it's for                                                |
|:----------------------------------------------------------------|:-------------------------------------------------------------|
| GET                                                             |
| `/api/v1/interactions`                                          | Returns All interactions                                     |
| `/api/v1/posts/filter?userhandle=&postname=&index=  `           | Returns The posts or post data with the information inserted |
| `/api/v1/posts/search/{searchTitle}/post `                      | Returns The post file with that search Title                 |
| POST                                                            |
| `/api/v1/posts/upload?file=&userhandle=&password=&description=` | Creates a new post                                           |
| PUT                                                             |
| `/api/v1/posts/update/searchTitle/{searchTitle}/post `          | Updates The post with that searchTitle                       |
| DELETE                                                          |
| `/api/v1/posts/delete?searchTitle= `                            | Deletes The post with that searchTitle                       |
