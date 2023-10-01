# 207-project

- Problem Domain: 
Want to create a music-based recommendation application that will generate playlists for the User based on their genre & mood input data.

- What kind of application we're developing:
Users choose from list of genres, and moods (and potentially artists and dates as well) and the application will use that selection of filters to generate customized playlists for the user.
This application's main purpose is to recommend playlists based on user taste & mood.

-  Link to the documentation for an API:
Link to Spotify API: https://developer.spotify.com/documentation/web-api

- Screenshot of using a tool to try out the API:
In this image, we use Postman to generate the desired JSON output using the tracks endpoint from the Spotify API (we used the ID of the song as a parameter to generate this output).
This outputs generates information about "Cut to the Feeling" by Carly Rae Jepsen. 

<img width="1680" alt="Screen Shot 2023-09-27 at 10 53 05 PM" src="https://github.com/NimratGrewal/207-project/assets/114554275/9c72908a-38b7-4a88-9928-c8b15152a8d0">

- Result of API call in Java:
<img width="1729" alt="API Call In Java" src="https://github.com/NimratGrewal/207-project/assets/85083456/1e1c918d-eb59-4abb-be40-2291cba5455e">

For our API call, we ran a request that gave us 10 songs which were recommended based upon the song "Cut to the Feeling"
by Carly Rae Jepsen. The request returns songs based upon similarity to the chosen input song(s), and is returned in the
format of a list.


