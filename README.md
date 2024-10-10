# RU
## Важно!
- Для работы Tab Player Highlighter необходима библиотека [oωo](https://modrinth.com/mod/owo-lib).
- Рекомендуется использовать [Notepad++](https://github.com/notepad-plus-plus/notepad-plus-plus) для редактирования JSON5 конфигурации.
- UUID игрока можно узнать на [NameMC](https://namemc.com/).
# Что делает мод?

<details>
<summary>Spoiler</summary>

TPH — модификация, позволяющая **закрепить** в табе игры определенных игроков по UUID.
### Выглядит это следующим образом:
![пример работы мода](https://cdn.modrinth.com/data/cached_images/89a71cbf85cd4e163eb81eefc998dba89dc8765e.png)

</details>


# Настройка конфига

<details>
<summary>Spoiler</summary>

Файл конфигурации находится по пути: 
```
.minecraft\config\tabplayerhighlighter.json5
```
И выглядит следующим образом:
```
{
	"players": [
		"UUID"
	]
}
```
На место UUID в конфигурационном файле мода Tab Player Highlighter вы должны указать необходимые вам идентификаторы игроков. Вы можете указать несколько UUID, чтобы выделить нескольких пользователей в игре. Приведу пример, как это должно выглядеть в вашем файле конфигурации:
```
{
	"players": [
		"613aff0a-909a-422c-9e81-4ff60671b55d",
		"9547165d-6601-4ab6-98c8-a4e026d1a4d8",
		"36dfd0bd-7b3d-4b35-8a16-0239b6ef3a37",
		"83174e19-8c75-4fff-bb75-0c8f407e6de8",
		"d7070bc5-fb79-49f2-b177-cefbbf5afeee",
		"318be6b4-a1bb-4b99-b422-004aad4c22c3",
		"148005f9-4cfe-46d8-bbe2-0edabfcb8c0a",
		"6a34aa61-ac3b-4252-a33e-b124b08bb3d1",
		"28a9431c-097d-46a1-b393-fde3802fba22",
		"6a640b67-87f0-456a-8f4d-a65aeb55f4f4",
		"5c5bc23d-a481-42e2-8fb2-f15f859fb394",
		"69da5b59-a80a-4fb3-a500-6cadcc38bc3b",
		"2c3193e6-e977-4c93-af1f-fd7463878c81"
	]
}
```
Соблюдайте правильную структуру текстового файла, чтобы модификация работала корректно.

</details>

# EN
## Important!
- The [oωo](https://modrinth.com/mod/owo-lib) library is required for Tab Player Highlighter to work.
- It is recommended to use [Notepad++](https://github.com/notepad-plus-plus/notepad-plus-plus) to edit the JSON5 configuration.
- The player's UUID can be found on [NameMC](https://namemc.com).
# What does the mod do?

<details>
<summary>Spoiler</summary>
  
TPH is a modification that allows you to **fix** in the game tab certain players by UUID.
### It looks like this:
  
![an example of how the mod works](https://cdn.modrinth.com/data/cached_images/89a71cbf85cd4e163eb81eefc998dba89dc8765e.png)

</details>

# Configuring the config

<details>
<summary>Spoiler</summary>

The configuration file is located at the path:
```
.minecraft\config\tabplayerhighlighter.json5
```
And looks like this:
```
{
	"players": [
		"UUID"
	]
}
```
In place of the UUID in the configuration file of the Tab Player Highlighter mod, you must specify the player IDs you need. You can specify multiple UUIDs to highlight multiple users in the game. Here is an example of how it should look in your configuration file:
```
{
	"players": [
		"613aff0a-909a-422c-9e81-4ff60671b55d",
		"9547165d-6601-4ab6-98c8-a4e026d1a4d8",
		"36dfd0bd-7b3d-4b35-8a16-0239b6ef3a37",
		"83174e19-8c75-4fff-bb75-0c8f407e6de8",
		"d7070bc5-fb79-49f2-b177-cefbbf5afeee",
		"318be6b4-a1bb-4b99-b422-004aad4c22c3",
		"148005f9-4cfe-46d8-bbe2-0edabfcb8c0a",
		"6a34aa61-ac3b-4252-a33e-b124b08bb3d1",
		"28a9431c-097d-46a1-b393-fde3802fba22",
		"6a640b67-87f0-456a-8f4d-a65aeb55f4f4",
		"5c5bc23d-a481-42e2-8fb2-f15f859fb394",
		"69da5b59-a80a-4fb3-a500-6cadcc38bc3b",
		"2c3193e6-e977-4c93-af1f-fd7463878c81"
	]
}
```
Follow the correct structure of the text file so that the modification works correctly.

</details>
