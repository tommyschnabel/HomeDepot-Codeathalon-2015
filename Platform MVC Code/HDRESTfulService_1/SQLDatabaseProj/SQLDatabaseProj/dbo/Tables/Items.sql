CREATE TABLE [dbo].[Items] (
    [itemID]      INT            IDENTITY (1, 1) NOT NULL,
    [typeID]      INT NOT NULL,
    [itemName]    NVARCHAR (MAX) NOT NULL,
    [Type_typeID] INT            NOT NULL,
    CONSTRAINT [PK_Items] PRIMARY KEY CLUSTERED ([itemID] ASC),
    CONSTRAINT [FK_TypesItems] FOREIGN KEY ([Type_typeID]) REFERENCES [dbo].[Types] ([typeID])
);


GO
CREATE NONCLUSTERED INDEX [IX_FK_TypesItems]
    ON [dbo].[Items]([Type_typeID] ASC);

