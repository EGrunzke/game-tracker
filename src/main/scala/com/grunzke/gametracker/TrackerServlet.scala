package com.grunzke.gametracker

import org.scalatra._
import scalate.ScalateSupport


class TrackerServlet extends GameTrackerStack {

  get("/") {
    contentType="text/html"
    jade("index.jade")
  }

  get("/player/list") {
    contentType="text/html"
    val ps = Db.query[Player].fetch()
    jade("player/list.jade", "ps" -> ps)
  }

  post("/player/create") {
    val name = params("name")
    val email = params("email")
    Db.save(Player(name, email))
    redirect("/player/list")
  }

  get("/gamedef/list") {
    contentType="text/html"
    val gameDefs = Db.query[GameDef].fetch()
    jade("gamedef/list.jade", "gameDefs" -> gameDefs)
  }

  post("/gamedef/create") {
    val name = params("name")
    val bggurl = params("bggurl")
    Db.save(GameDef(name, bggurl))
    redirect("/gamedef/list")
  }
}
