# Linky

Linky is a Minecraft plugin to eliminate the downsides of typical linking systems.

## Why?

Typical Discord linking solutions require a good bit of work on the developers side. Like hosting their own bot within a plugin, for example. That takes up resources and isn't the easiest for newcomers to set up.

Linky aims to improve upon this, by making the experience for both administrators AND users seamless.

## Setup

1. Head over to the [hosted version](https://linky.astrid.sh/) of the panel, and create an account.
2. Create an instance for a particular Discord server, and invite the bot.
3. Download and install the latest plugin release from [here](https://github.com/linkymc/Plugin/releases/tag/latest)
4. Input your API key from the panel into `config.yml`, and you're done :)

## Skript integration

Linky integrates in Skript, so you can easily get Linky data.

<details>
    <summary>Get Discord ID</summary>
<br/>
  Fetches the Discord ID of a player

```
discord id of %player%
```
</details>

<details>
    <summary>Get Linked Status</summary>
<br/>
  Condition to check whether a player is linked or not

```
%player% (is|has) linked
%player% (is|has) not linked
```
</details>

<details>
    <summary>Get Member Status</summary>
<br/>
  Condition to check whether a player is in your Discord server.

```
%player% is in [the] discord [server]
%player% is not in [the] discord [server]
```
</details>