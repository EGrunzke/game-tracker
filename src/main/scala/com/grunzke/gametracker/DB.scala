package com.grunzke.gametracker

import sorm._
import org.joda.time.LocalDate

case class Player (
  name : String,
  email : String
)

case class GameDef (
  name : String,
  bggurl : String
)

case class Expansion (
  parent : GameDef,
  name : String,
  bggurl : String
)

case class Game (
  date : LocalDate,
  core : GameDef,
  expansions : Set[Expansion],
  players : Set[Player],
  playerCount : Int,
  note: String
)

case class Score (
  game : Game,
  player : Player,
  value : Int,
  rank : Int,
  faction: String,
  players : Set[Player],
  note: String
)

object Db extends Instance(
  entities = Set(
    Entity[Player](),
    Entity[GameDef](),
    Entity[Expansion](),
    Entity[Game](indexed = Set() + Seq("core") + Seq("core", "expansions") + Seq("players")),
    Entity[Score]()
  ),
  url = "jdbc:h2:mem:test",
  user = "",
  password = "",
  initMode = InitMode.Create
) {
    def noop() {}
}