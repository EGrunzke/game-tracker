import com.grunzke.gametracker._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    Db.noop()
    context.mount(new TrackerServlet, "/*")
  }

  override def destroy(context: ServletContext) {
    Db.close()
  }
}
