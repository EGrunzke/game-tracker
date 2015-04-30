package com.grunzke.gametracker

import org.scalatra._
import scalate.ScalateSupport


class TrackerServlet extends GameTrackerStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world! :-)</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/init") {
    Db.save(Player("Eric Grunzke", "eric@grunzke.com"))
    Db.save(Player("David Moffett", "david.moffett1@gmail.com"))
    "Made two peeps"
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
}
