
-- --------------------------------------------------
-- Entity Designer DDL Script for SQL Server 2005, 2008, 2012 and Azure
-- --------------------------------------------------
-- Date Created: 02/28/2015 01:36:27
-- Generated from EDMX file: C:\Users\Vodrie\documents\visual studio 2013\Projects\HDRESTfulService_1\HDRESTful_Service\Models\HDDatabaseEDM.edmx
-- --------------------------------------------------

SET QUOTED_IDENTIFIER OFF;
GO
USE [HDDatabase];
GO
IF SCHEMA_ID(N'dbo') IS NULL EXECUTE(N'CREATE SCHEMA [dbo]');
GO

-- --------------------------------------------------
-- Dropping existing FOREIGN KEY constraints
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[FK_TypesItems]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Items] DROP CONSTRAINT [FK_TypesItems];
GO

-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[Types]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Types];
GO
IF OBJECT_ID(N'[dbo].[Items]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Items];
GO

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

-- Creating table 'Types'
CREATE TABLE [dbo].[Types] (
    [typeID] int IDENTITY(1,1) NOT NULL,
    [typeName] nvarchar(max)  NOT NULL,
    [Lat] float  NOT NULL,
    [Lng] float  NOT NULL
);
GO

-- Creating table 'Items'
CREATE TABLE [dbo].[Items] (
    [itemID] int IDENTITY(1,1) NOT NULL,
    [typeID] nvarchar(max)  NOT NULL,
    [itemName] nvarchar(max)  NOT NULL,
    [Type_typeID] int  NOT NULL
);
GO

-- --------------------------------------------------
-- Creating all PRIMARY KEY constraints
-- --------------------------------------------------

-- Creating primary key on [typeID] in table 'Types'
ALTER TABLE [dbo].[Types]
ADD CONSTRAINT [PK_Types]
    PRIMARY KEY CLUSTERED ([typeID] ASC);
GO

-- Creating primary key on [itemID] in table 'Items'
ALTER TABLE [dbo].[Items]
ADD CONSTRAINT [PK_Items]
    PRIMARY KEY CLUSTERED ([itemID] ASC);
GO

-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- Creating foreign key on [Type_typeID] in table 'Items'
ALTER TABLE [dbo].[Items]
ADD CONSTRAINT [FK_TypesItems]
    FOREIGN KEY ([Type_typeID])
    REFERENCES [dbo].[Types]
        ([typeID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_TypesItems'
CREATE INDEX [IX_FK_TypesItems]
ON [dbo].[Items]
    ([Type_typeID]);
GO

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------