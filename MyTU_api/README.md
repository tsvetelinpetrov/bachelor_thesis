# MyTU_api

## Installation

MyTU_api requires [VS Studio 2022](https://visualstudio.microsoft.com/vs/) and .NET 6.0 to build and run.

### Migrations
Open Tools -> NuGet Package Manager -> Packet Manager Console (<kbd>Alt</kbd> + <kbd>T</kbd> + <kbd>N</kbd> + <kbd>O</kbd>)

#### Create new migration
```sh
cd MyTU_api.Infrastructure
dotnet ef migrations add MIGRATION_NAME --startup-project ../MyTU_api
```

#### Update the database

```sh
cd MyTU_api.Infrastructure
dotnet ef database update --startup-project ../MyTU_api
```

# API
| Method | URL                                    | Description                                             | Implemented |
|--------|----------------------------------------|---------------------------------------------------------|-------------|
| `GET`  | `/api/building`                        | [List of all buildings, floors & rooms](#list)          | Yes		  |
| `GET`  | `/api/building/details/{buildingID}`   | [Full info fot the building](#list)                     | Yes		  |
| `GET`  | `/api/room/details/{roomId}`           | [Full info fot the room](#list)                         | Yes		  |
| `GET`  | `/api/navigation/getRoute/\{locationType2}/{locationId1}/{locationId2}`           | [Route from one node to another](#list)                         | Yes		  |
| `GET`  | `/health`                              | Server health check: returns 200 `OK`            		| No          |