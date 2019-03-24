# Project: Objects save themselves implicitly

## Methods we have to check

### MovieImpl

- no methods here?

### MovieListImpl

- [ ] Constructor(String, String, MovieImpl) (only the main one, that does everything)
- [ ] addMovie()
- [ ] addMovies()
- [ ] deleteMovie()
- [ ] addUser()
- [ ] getId () // should only work when isPersisted = true
- [ ] setName()
- [ ] setMovies()
- [ ] setMovieListId() //?

### User

- [ ] NOT THE Constructor(String, String) //because persist has to be called once
- [ ] addMovieList()
- [ ] deleteMovieList()
- [ ] setMovieLists // this one is private though so maybe not? 
