##

## MUSIC APP

In this project, we'll focus on showing the use of DS in software development. The focus of this project is to create a music app with playlists, music and users.

## DOMAIN // MODEL
We designed our Model classes based on the domain concept -> The domain are the classes that represent real concepts of the system and they protect they'r own rules to avoid null or invalid states. We look more into it in the Mod 4, tutoria 3. 

## FINAL REPOSITORY
The repository should be a final field in the service layer. This so we ensure the reference of that repository can't be changed (we can still change its internal data).

## FIELDS IN THE SERVICE LAYER
Having a List is not a good Idea, Because it means the Service has state. The Services are stateless. The best we can do is create local variables using repo.getAllDishes().

Services should be stateless. If we store mutable data (List, DS...) as a field, it introduces state into the service. The repo can be called from inside the methods and their results saved in local variables to operate with them. 

## REPO FOR PLAYLISTS?

We don't use a repository for Playlists, because they're not an aggregate root in our system ||||||||||||| an aggregate root is an object that ownns lyfecycle and consistency of related entities: an aggregate is a cluster of domain objects, it can be the fields, they live and die as a unit.
an aggregate root: 
- can be created alone
- can be stored alone
- can be retrieved alone
- has its own lifecycle
as an example, we have User and Song. Unlike Playlist, they can live on their own.||||||||||| 

