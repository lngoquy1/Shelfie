# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Started this file on November 3rd because we didn't realize we needed a changelog before.
### However, we did work before Nov. 3rd! Here is everything we did before then:
### Set up
- Got IntelliJ set up (took most of our time)

### Added
- Author class
- Genre class
- Position class
- Book class
- RowShelf class
- BookShelf class
- HibBook class

## 2017-11-03
### Added
- AllShelves interface
- Book interface
- BookShelf interface
- CreatedList interface
- Publisher class
- ReadingList class
- RowShelf interface
- ShelvedBook interface
- SimpleShelvedBook class
- SortType class
- Title class
- WishList class
- BookTests class
- HibBook class
- ItemOneFragment class
- ItemTwoFragment class
- ItemThree Fragment class
- MainActivity class

### renamed
- Book class to SimpleBook class
- BookShelf class to  SimpleBookShelf class
- Row class to SimpleRowShelf class
- HibernateTest class to HibTest
- HibernateUtil class to HibUtil
- renamed Title, Author, Genre, Publisher to SimpleTitle, SimpleAuthor, SimpleGenre, SimplePublisher
- renamed ItemOneFragment class to fragment_camera.xml
- renamed ItemTwoFragment class to fragment_connect.xml
- renamed ItemThreeFragment class to fragment_profile.xml


### Removed
- Position class

### Current classes as of 2017-11-3, 9pm
- AllShelves interface
- Author interface
- Book interface
- BookShelf interface
- ContactList class
- CreatedList interface
- Genre class
- Main class (contents are temporary)
- Position class
- Publisher class
- ReadingList class
- RowShelf interface
- ShelvedBook interface
- SimpleAuthor class
- SimpleBook class
- SimpleBookShelf class
- SimpleGenre class
- SimplePublisher class
- SimpleRowshelf class
- SimpleShelvedBook class
- SortType class
- Title class
- User class
- WishList class

  Hibernate:
- BookTests class
- HibAuthor class
- HibBook class
- HibTest class
- HibUtil class
- HibRowShelf
- HibShelvedBook

  Android:
- ItemOneFragment class
- ItemTwoFragment class
- ItemThreeFragment class
- MainActivity class

### Heavily Edited
- All Hibernate files

### Created on 2017-11-4
- Login class
- Goodreads class
- OAuthExample class
- GoodreadsTests
- HashPasswordTests: tests hashing passwords

  Hibernate/spark:
- Server class

  Android:
- fragment_search.xml
- fragment_shelf.xml

### Created on 2017-11-5
- ServerExample

### Updated on 2017-11-5
- HibTest class: tests hibernate classes
- HibBook class
- HibTitle class
- HipAuthor class
- Server class: essentially the code Zach walked through during class. For reference.
- ServerExample class: attempts to use spark to post a visual representation of
  the database on a webpage.

  Updated for Facebook GUI login:
- Strings.xml
- AndroidManifest.xml
- build.gradle
- MainActivity class
- activity_main.xml
- activity_shelved_login_5.xml
- content_shelved_login_5.xml

### Deleted on 2017-11-5
- HibUtil

### General status update as of 2017-11-5
Database is fully working. Server tries to send representation of database to webpage, but has issues (error code 100 - coming to office hours).
Facebook login button appears but does not function. However, we have a prototype which allows login, so this behavior is concerning, and
we will also be coming to office hours for this. Five buttons at bottom of the screen are present, and clicking on them bring up blank pages for each.
We created almost all of the classes (including hibernate versions). We implemented some Goodreads API queries, and also used the API to scrape their page
when their API was being belligerent. We also used JBCrypt hashing password API to hash passwords.
