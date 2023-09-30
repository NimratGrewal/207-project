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
  
<img width="1680" alt="Screen Shot 2023-09-27 at 10 53 05 PM" src="https://github.com/NimratGrewal/207-project/assets/114554275/22f1903c-6dc3-41c2-aca4-4a192552f0bf">

- Result of API call in Java:
  
<img width="1015" alt="Screen Shot 2023-09-30 at 11 54 25 AM" src="https://github.com/NimratGrewal/207-project/assets/114554275/a648e0fd-636f-4288-8041-0e1871296249">

Running the request results in a status code 200, which means the API call is successfull. 
