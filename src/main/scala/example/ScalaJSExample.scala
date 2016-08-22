package example

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

import scala.scalajs.js
import scala.util.Random

object ScalaJSExample extends js.JSApp
  with JavaScriptContextImpl
  with CanvasConfiguration
  with GameContext {
  override def main(): Unit = {

    js.timers.setInterval(20){
      run
    }
    ctxCanvasState.canvas.onclick = (e: dom.MouseEvent) => {
      playerV -= 5
    }

  }



  def runLive(implicit ctxCanvasState: CtxCanvasState) = {
    frame += 2

    // Create new obstacles, or kill old ones as necessary
    if (frame >= 0 && frame % obstacleGap == 0){
      obstacles.enqueue(Random.nextInt(ctxCanvasState.canvas.height - 2 * holeSize) + holeSize)
    }

    if (obstacles.length > 7){
      obstacles.dequeue()
      frame -= obstacleGap
    }

    // Apply physics
    playerY = playerY + playerV
    playerV = playerV + gravity


    // Render obstacles, and check for collision
    ctxCanvasState.ctx2d.fillStyle = "darkblue"
    for((holeY, i) <- obstacles.zipWithIndex){
      // Where each obstacle appears depends on what frame it is.
      // This is what keeps the obstacles moving to the left as time passes.
      val holeX = i * obstacleGap - frame + ctxCanvasState.canvas.width
      ctxCanvasState.ctx2d.fillRect(holeX, 0, 5, holeY - holeSize)
      ctxCanvasState.ctx2d.fillRect(
        holeX, holeY + holeSize, 5, ctxCanvasState.canvas.height - holeY - holeSize
      )

      // Kill the player if he hits some obstacle
      if (math.abs(holeX - ctxCanvasState.canvas.width/2) < 5 &&
        math.abs(holeY - playerY) > holeSize){
        dead = 50
      }
    }

    // Render player
    ctxCanvasState.ctx2d.fillStyle = "darkgreen"
    ctxCanvasState.ctx2d.fillRect(ctxCanvasState.canvas.width / 2 - 5, playerY - 5, 10, 10)

    // Check for out-of-bounds player
    if (playerY < 0 || playerY > ctxCanvasState.canvas.height){
      dead = 50
    }
  }

  def runDead(implicit ctxCanvasState: CtxCanvasState) = {
    playerY = ctxCanvasState.canvas.height / 2
    playerV = 0
    frame = -50
    obstacles.clear()
    dead -= 1
    ctxCanvasState.ctx2d.fillStyle = "darkred"
    ctxCanvasState.ctx2d.fillText("Game Over", ctxCanvasState.canvas.width / 2, ctxCanvasState.canvas.height / 2)
  }

  def run(implicit ctxCanvasState: CtxCanvasState) = {
    ctxCanvasState.ctx2d.clearRect(0, 0, ctxCanvasState.canvas.width, ctxCanvasState.canvas.height)
    if (dead > 0) runDead
    else runLive
  }


}


trait JavaScriptContextImpl extends JavaScriptContext {

  private val canvas: Canvas = dom.document.getElementById("canvas").asInstanceOf[Canvas]
  private val ctx2d: dom.CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  override implicit lazy val ctxCanvasState = CtxCanvasState(canvas, ctx2d)
}