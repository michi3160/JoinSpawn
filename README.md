# JoinSpawn Plugin
# 
Version: 1.0
Minecraft: 1.21.x (Spigot / Paper)
Java: 17+

## Description

JoinSpawn is a lightweight Minecraft plugin that ensures players always spawn at a fixed location when they join the server. The spawn point can be easily set by an authorized player using a simple command, and it automatically updates the configuration file.

This plugin works with BungeeCord setups, as it runs on each Spigot/Paper server individually, ensuring consistent join spawn behavior across multiple servers.

## Features

Teleports players to a fixed location on join.

Configurable spawn coordinates (x, y, z, yaw, pitch) in config.yml.

Set spawn using an in-game command /setjp <x> <y> <z> [yaw] [pitch].

Automatically saves changes to config.yml.

Supports multiple servers in a BungeeCord network if installed on each server.

Simple and lightweight, no additional dependencies.

Commands
/setjp <x> <y> <z> [yaw] [pitch]

Sets the join spawn location to the specified coordinates and optional orientation.

x, y, z — required coordinates.

yaw, pitch — optional rotation (default 0).
Permission: joinspawn.set (default OP only)

Example:

/setjp 100 64 200 90 0

Configuration (config.yml)

```
spawn:
  world: world      # World name
  x: 0.5            # X coordinate
  y: 64             # Y coordinate
  z: 0.5            # Z coordinate
  yaw: 0            # Rotation yaw
  pitch: 0          # Rotation pitch

```
