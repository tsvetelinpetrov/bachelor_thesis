# MyTU_api

## Installation

MyTU_api requires [VS Studio 2022](https://visualstudio.microsoft.com/vs/) and .NET 6.0 to build and run.

### Migrations
Open Tools -> NuGet Package Manager -> Packet Manager Console (Alt + T + N + O)

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