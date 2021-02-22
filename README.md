# Tivi

It is a simple app that lists the Tv shows.

The app makes use of the apis from, https://developers.themoviedb.org/ to fetch the TV shows.

On launching the app,the first screen lists the popular shows,with an option to "Favourite" or "un-favourite" a show.

Clicking on any one of the list item,leads to the second screen, which shows the details about the tv show.

The first screen,has a "Favourites" menu option,which takes the user to the "Favourites" screen.

The app makes use of MVVM - DataBinding - Live Data.

Network : OkHttp

Dependency Injection : Dagger

ImageLoading : Picaso

Persistence : Room

Background tasks : Kotlin Co-routines
