
***
# _API Documentation_ 
***
## Media-Type
* All endpoints that end successfully return in [application/vnd.siren+json](https://github.com/kevinswiber/siren).
* Errors are represented in [application/problem+json](https://tools.ietf.org/html/rfc7807) specification whenever possible.
* When a request needs to have data passed in the body, the data must be in [application/json](https://tools.ietf.org/html/rfc4627).

## Data
* Data collections containing dates, are sorted in chronological order, from most recent to latest.
* Dates are represented in the following format YYYY-MM-DDThh:mm:ss.SSSZ. The letters meaning is specified in the following [link](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html).

## Endpoint Security
* Endpoints that need authorization to request, should provide authorization information using [HTTP Basic scheme](https://tools.ietf.org/html/rfc7617). _**[Authentication mechanism may change]**_.
* Username used should not contain **spaces** or, as specified in the previous RFC, the character **':'**.
* In the _Endpoint_ section, every endpoint marked with '**[Requires Authentication]**', should use this authentication mechanism.

## Documentation Dictionary

## Terminology
### Constants
* **Collection** - When placed as one of the entries in array '_class_' of siren+json, means that the response is a list of items, where each item is a resource that represents the remaining entries of array '_class_'. (ex : "class": ["Collection", "Project"])
* **User** - Represents an authenticated user.
* **Project** - Represents a a long-running development activity that belongs to an _user_ and can have _issues_, _labels_, _states_ and _state transitions_ associated with it.
  * **Project Available Label** - Represents a label allowed to be associated to an _issue_ of a certain _project_.
  * **Project Available State** - Represents a state allowed to be associated to an _issue_ of a certain _project_.
  * **Project State Transition** - Represents a transition from one _state_ to another, allowed for an _issue_ of a certain _project_.
* **Issue** - Represents a task that needs to be done in the context of a _project_, such as adding a new functionality, resolve an error, add a test, create a final release.
  * **Issue Label** - Represents a label associated with a certain _Issue_.
  * **Issue Comment** - Represents a comment made by a _user_ to a certain _issue_.

### Variables
* **username** - Represents the username of a authenticated _user_.
* **projectName** - Represents the name of a _project_.
* **issueId** - Represents the Id of a _issue_.
* **commentId** - Represents the Id of a _comment_.
* **labelName** - Represents the name of a label.
* **stateName** - Represents the name of a state.
* **fromState** - Represents the name of the origin state in a state transition.
* **toState** - Represents the name of the destiny state in a state transition.

**NOTE**: When any of the variables is presented in the documentation between curly braces, should be replaced by the actual value when used.

***
# _Endpoints_
***
## URI Templates
  `GET /`

  URI templates to all resources in API.

  Templates are formed in compliance with [RFC 6570 - URI Template](https://tools.ietf.org/html/rfc6570).
```json
{
    "users_url": "/users",
    "authenticate_url": "/users/authenticate",
    "user_url": "/users/{username}",
    "user_projects_url": "/projects{?username}",
    "project_url": "/projects/{projectName}",
    "project_labels_url": "/projects/available-labels{?projectName,labelName}",
    "project_states_url": "/projects/available-states{?projectName,stateName}",
    "project_state_transitions_url": "/projects/state-transitions{?projectName,fromState,toState}",
    "project_issues_url": "/issues{?projectName}",
    "issue_url": "/issues/{issueId}",
    "issue_comments_url": "/issues/comments{?issueId}",
    "issue_comment_url": "/issues/comments/{commentId}",
    "issue_labels_url": "/issues/labels{?issueId,labelName}"
}
```
## User
* ### Create User
  `POST /users`

  Creates a new user with data passed in request body.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | username | String | Unique username that identifies user and is used in [authentication](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#endpoint-security). |
    | password | String | Password for user authentication. |
    | email | String | User email. |
    | fullName | String | User complete name. |
    
* ### Authenticate User
  `POST /users/authenticate`

  Request with header Authorization in compliance with [HTTP Basic scheme](https://tools.ietf.org/html/rfc7617) to authenticate user credentials.

* ### Get User By Username
  `GET /users/{username}`

  Returns the state of a single entity.

  * Properties:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | username | String | username that identifies user. |
    | email | String | User email. |
    | fullName | String | User complete name. |

  * Embebbed Links: 

    |    rel    | Description |
    | --------- | ----------- |
    | /rels/user-projects | Link to request collection of user projects. |

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | update-user | Action to update user represented in entity. | [update user fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#update-user-requires-authentication) |
    | delete-user | Action to delete user represented in entity. | [delete user fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#delete-user-requires-authentication) |
  
* ### Update User [Requires Authentication]
  `PUT /users/{username}`

  Updates a specific user with data passed in request body.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | email | String | User email. |
    | fullName | String | User complete name. |

* ### Delete User [Requires Authentication]
  `DELETE /users/{username}`

    Deletes a specific user.

## Project
* ### Create Project [Requires Authentication]
  `POST /projects`

  Creates a new project with data passed in request body.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | name | String | Unique name that identifies the project. | 
    | description | String | Small description of the project being created. |
    | username | String | Username of the owner of this project. |
    | defaultStateName | String | Default state for the issues of this project when first created. |

* ### Get User Projects
  `GET /projects?username={username}`

  Returns a collection.

  * Entities: (Each sub-entity represents a project)
    
    **rel:** /rels/user-project

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | owner | String | Username of the owner of this project. | 
    | name | String | Unique name that identifies the project. | 
    | description | String | Small description of the project being created. |
    | defaultStateName | String | Default state for the issues of this project when first created. |

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | create-project | Action to create a new project. | [Create project fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#create-project-requires-authentication) |

* ### Get Project By Name
  `GET /projects/{projectName}`

  Returns the state of a single entity.

  * Properties:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | owner | String | Username of the owner of this project. | 
    | name | String | Unique name that identifies the project. | 
    | description | String | Small description of the project being created. |
    | defaultStateName | String | Default state for the issues of this project when first created. |

  * Embebbed Links: 

    |    rel    | Description |
    | --------- | ----------- |
    | /rels/project-available-labels | Link to request collection of project available labels. |
    | /rels/project-available-states | Link to request collection of project available states. |
    | /rels/project-state-transitions | Link to request collection of project possible state transitions. |
    | /rels/project-issues | Link to request collection of project issues. |

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | update-project | Action to update project represented in entity. | [update project fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#update-project-requires-authentication) |
    | delete-project | Action to delete project represented in entity. | [delete project fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#delete-project-requires-authentication) |

* ### Update Project [Requires Authentication]
  `PUT /projects/{projectName}`

  Updates a specific project with data passed in request body.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | description | String | Small description of the project being updated. |
    | defaultStateName | String | Default state for the issues of this project when first created. |

* ### Delete Project [Requires Authentication]
  `DELETE /projects/{projectName}`

  Deletes a specific project.

## Project Available Label
* ### Add Project Available Label [Requires Authentication]
  `POST /projects/available-labels`

  Creates a new label, with data passed in request body, and adds it to the project available labels.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | projectName | String | Name of the project to add label. | 
    | labelName | String | Name of the label to add. |

* ### Get Project Available Labels
  `GET /projects/available-labels?projectName={projectName}`

  Returns a collection.

  * Entities: (Each sub-entity represents a project available label.)
    
    **rel:** /rels/project-available-label

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | projectName | String | Name of the project. | 
    | labelName | String | Name of the label. |

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | add-project-available-label | Action to add a new label to a project available labels. | [Add Project Available Label fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#add-project-available-label-requires-authentication) |

* ### Delete Project Available Label [Requires Authentication]
  `DELETE /projects/available-labels?projectName={projectName}&labelName={labelName}`

  Deletes a label from a project available labels.

## Project Available State
* ### Add Project Available State [Requires Authentication]
  `POST /projects/available-states`

    Creates a new state, with data passed in request body, and adds it to the project available states.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | projectName | String | Name of the project to add label. | 
    | stateName | String | Name of the state to add. |

* ### Get Project Available States
  `GET /projects/available-states?projectName={projectName}`

  Returns a collection.

  * Entities: (Each sub-entity represents a project available state.)
    
    **rel:** /rels/project-available-state

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | projectName | String | Name of the project. | 
    | stateName | String | Name of the state. |

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | add-project-available-state | Action to add a new state to a project available states collection. | [Add Project Available State fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#add-project-available-state-requires-authentication) |

* ### Delete Project Available State [Requires Authentication]
  `DELETE /projects/available-states?projectName={projectName}&stateName={stateName}`

  Deletes a state from a project available states.

## Project State Transition
* ### Add Project State Transition [Requires Authentication]
  `POST /projects/state-transitions`

    Creates a new state transition, with data passed in request body, and adds it to the project state transitions.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | projectName | String | Name of the project to add label. | 
    | fromState | String | Name of the state of origin. |
    | toState | String | Name of the state of destiny. |

* ### Get Project State Transitions
  `GET /projects/state-transitions?projectName={projectName}`

  Get possible state transitions for specific project. 

  Returns a collection.

  * Entities: (Each sub-entity represents a project state transition.)
    
    **rel:** /rels/project-state-transition

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | projectName | String | Name of the project. | 
    | fromState | String | Name of the state of origin. |
    | toState | String | Name of the state of destiny. |

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | add-project-state-transition | Action to add a new state transition to a project state transitions collection. | [Add Project State Transition fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#add-project-state-transition-requires-authentication) |

* ### Delete Project State Transition [Requires Authentication]
  `DELETE /projects/state-transitions?projectName={projectName}&fromState={fromState}&toState={toState}`

  Deletes state transition from project possible state transitions.

## Issue
* ### Create Issue [Requires Authentication]
  `POST /issues`

  Creates a new issue with data passed in request body.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | issueCreator | String | Username of the user who is creating the issue. |
    | projectName | String | Unique name that identifies the project the issue is about. | 
    | issueName | String | Small name/title of the issue. |
    | description | String | Description of the issue being created. |

* ### Get Project Issues
  `GET /issues?projectName={projectName}`

  Returns a collection.

  * Entities: (Each sub-entity represents an issue)
    
    **rel:** /rels/project-issue

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | id | Int | Unique id that identifies the issue. |
    | issueCreator **(May not exist)** | String | Username of the user who created the issue. |
    | projectName | String | Unique name that identifies the project the issue is about. | 
    | issueName | String | Small name/title of the issue. |
    | description | String | Description of the issue being created. |
    | creationDate | Date | Date of the creation of the issue. | 
    | closeDate **(May not exist)** | Date | Date when issue was closed or archived. |
    | state | String | Current issue state. |

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | create-issue | Action to create a new issue. | [Create issue fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#create-issue-requires-authentication) |

* ### Get Issue By Id
  `GET /issues/{issuesId}`

  Returns the state of a single entity.

  * Properties:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | id | Int | Unique id that identifies the issue. |
    | issueCreator **(May not exist)** | String | Username of the user who created the issue. |
    | projectName | String | Unique name that identifies the project the issue is about. | 
    | issueName | String | Small name/title of the issue. |
    | description | String | Description of the issue being created. |
    | creationDate | Date | Date of the creation of the issue. | 
    | closeDate **(May not exist)** | Date | Date when issue was closed or archived. |
    | state | String | Current issue state. |

  * Embebbed Links: 

    |    rel    | Description |
    | --------- | ----------- |
    | /rels/issue-comments | Link to request collection of issue comments. |
    | /rels/issue-labels | Link to request collection of issue associated labels. |

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | update-issue | Action to update issue represented in entity. | [update issue fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#update-issue-requires-authentication) |
    | delete-issue | Action to delete issue represented in entity. | [delete issue fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#delete-issue-requires-authentication) |

* ### Update Issue [Requires Authentication]
  `PUT /issues/{issuesId}`

  Updates a specific issue with data passed in request body.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | issueName | String | Small name/title of the issue. |
    | description | String | Description of the issue. |
    | state | String | issue state. |

* ### Delete Issue [Requires Authentication]
  `DELETE /issues/{issuesId}`

  Deletes a specific issue.

## Issue Label
* ### Add Issue Label [Requires Authentication]
  `POST /issues/labels`

  Associates a label with a issue, with data passed in request body, if the label is allowed for this issue.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | issueId | Int | Identifier of issue to associate label. | 
    | labelName | String | Name of the label to associate with issue. |

* ### Get Issue Labels
  `GET /issues/labels?issueId={issueId}`

  Returns a collection.

  * Entities: (Each sub-entity represents a issue label.)
    
    **rel:** /rels/issue-label

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | projectName | String | Name of the issue project. | 
    | issueId | Int | Identifier of issue. | 
    | labelName | String | Name of the label. |

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | add-issue-label | Action to associate a label to a issue. | [Add Issue Label fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#add-issue-label-requires-authentication) |

* ### Delete Issue Label [Requires Authentication]
  `DELETE /issues/labels?issueId={issueId}&labelName={labelName}`

  Deletes the association of a label to an issue.

## Issue Comment
* ### Create Issue Comment [Requires Authentication]
  `POST /issues/comments`

  Creates a comment for a specific issue.
  * Request Body:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | issueId | Int | Identifier of issue. | 
    | commentCreator | String | Username of the user creating the comment. |
    | content | String | Content of the commentary. |

* ### Get Issue Comments
  `GET /issues/comments?issueId={issueId}`

  Returns a collection.

  * Entities: (Each sub-entity represents a issue comment.)
    
    **rel:** /rels/issue-comment

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | id | Int | Unique identifier that identifies the comment. |
    | commentCreator **(May not exist)** | String | Username of the user who created the comment. |
    | issueId | Int | Identifier of issue. | 
    | content | String | Content of the commentary. |
    | creationDate | Date | Date of the creation of the comment. | 

  * Actions: 

    |   Name   | Description | Fields |
    | -------- | ----------- | ------ |
    | create-issue-comment | Action to create a comment to a issue. | [Create Issue Comment fields description.](https://github.com/pina14/daw-project-manager/wiki/API-Documentation/#create-issue-comment-requires-authentication) |

* ### Get Comment By Id
  `GET /issues/comments/{commentId}`

  Returns the state of a single entity.

  * Properties:

    | Name | Type | Description |
    | ---- | ---- | ----------- |
    | id | Int | Unique identifier that identifies the comment. |
    | commentCreator **(May not exist)** | String | Username of the user who created the comment. |
    | issueId | Int | Identifier of issue. | 
    | content | String | Content of the commentary. |
    | creationDate | Date | Date of the creation of the comment. | 
