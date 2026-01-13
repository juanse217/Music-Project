##

## MUSIC APP

In this project, we'll focus on showing the use of DS in software development. The focus of this project is to create a music app with playlists, music and users.

# DOMAIN // MODEL
We designed our Model classes based on the domain concept -> The **domain are the classes that represent real concepts of the system and they protect they'r own rules to avoid null or invalid states**. We look more into it in the Mod 4, tutoria 3. 

# FINAL REPOSITORY
The repository should be a final field in the service layer. This so we ensure **the reference of that repository can't be changed (we can still change its internal data)**.

# FIELDS IN THE SERVICE LAYER
Having a List is not a good Idea, Because it means the Service has state. **The Services are stateless**. The best we can do is create local variables using repo.getAllDishes().

Services should be stateless. **If we store mutable data (List, DS...) as a field, it introduces state into the service**. The repo can be called from inside the methods and their results saved in local variables to operate with them. 

# REPO FOR PLAYLISTS?

We don't use a repository for Playlists, because they're not an **aggregate root** in our system ||||||||||||| an aggregate root is an object that ownns lyfecycle and consistency of related entities: an aggregate is a cluster of domain objects, it can be the fields, they live and die as a unit.
an aggregate root: 
- can be created alone
- can be stored alone
- can be retrieved alone
- has its own lifecycle
as an example, we have User and Song. Unlike Playlist, they can live on their own.||||||||||| 

# Repository methods
In the repositories we look to answer certain questions like Which user exists, return X user or save X user. **We don't ask to access the internal information of the User or class for that matter**. 

If we added a method to access the library of a User, **we'd have 2 ways of doing the same which would be a code Smell**. We can add this functionality in the Service, we retrieve the User and then get the library

__Mental rule you should write down__
- Repositories return aggregate roots, not their internal parts.
__And__:
- If you can get it by navigating the domain object, the repository doesn’t need to expose it.

# Exceptions
We only use the UserNotFoundException and the SongNotFoundException.  This helps improving readability and debugging. These represend meaningful failures. We make them Runtime because it means the system is in a state where the operation cannot continue. 

# Interfaces

In java, when using interfaces, there's no need to add the I at the beginning. There's no need to specify what they're returning or saving, it's implicit what they're going to return. 

# Find By (X) besides the id

There's a gray area in this situation. These can be methods that can belong to the service layer; we can have them in the repositories in academic projects, but a more stable and pure design is by adding these methods to the service. 

If we add queries to the repository these should be purely data-based: like getting by artist, genre or id. It's just returning based on something we already have, it would be wrong if we had queries in repositories like "top 10 shortest songs" or things that require certain rules. 

No business rules inside repositories. If a query requires "thinking", they should go to the service: llike what's popular or what the user can see. 

**If the method can be implemented as a simple WHERE clause, it may belong in the repository. If it requires reasoning, ranking, permissions, or combining rules, it belongs in the service.**

# Service classes

We shouldn't have just one service class, this can grow into a god-class that manages everything, it can be wrong and cause issues. It's better to have one service for each aggregate root (classes in our domain). The service helps us model a concept. 

We can still use, in this case, the SongRepository in our UserService; we do it because the focus is the User but we might need to add songs to a user Playlist. 

__A service may use multiple repositories, but it should serve one business concept.__

# Service Interface

Only introduce an interface when you have (or realistically expect) more than one implementation. Like multiple payment methods or so. 

# Reusing the service method

It's best to reuse a method like "findById" in our service becaue it already has a certain policy and handles certain cases. If we call the repo to get a user by id, we'd need to accept there will be errors or implement the same policy of the method. We also reuse the findPlaylistById() since it has the rule/policy we need to get that info. 

# Returning List<> or Collection<> on the service

When we're working with services and are going to return multiple items, it's best to use the List<> interface instead of Collection<>, the latter fits best when used in Repositories and Domain.

__You choose the return type based on the semantic guarantee you want to give.__ 

### Collection<T>
**Means**:
- “You can iterate”
- “No guarantees about order or access”
- “Minimal contract”
**Use when**:
- You don’t care how it’s used
-You don’t want to promise anything

### List<T>
**Means**:
- Ordered
-Indexable
- Predictable iteration
**Use when**:
- Order matters (even implicitly)
- You expect UI / iteration / processing
- You want convenience

This is not premature optimization.

### Concrete rule you can write down
Repositories return the weakest abstraction possible.
Services return the most useful abstraction reasonable.

### Final answer (one sentence)
Yes — prefer List<T> in services when you expect ordered, linear use; use Collection<T> when you truly don’t care.

# 

