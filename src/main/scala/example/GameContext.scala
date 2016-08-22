package example

trait GameContext {
  self: JavaScriptContext =>

  /*variables*/
  val obstacleGap = 200 // Gap between the approaching obstacles
  val holeSize = 50     // Size of the hole in each obstacle you must go through
  val gravity = 0.1     // Y acceleration of the player

  var playerY = ctxCanvasState.canvas.height / 2.0 // Y position of the player; X is fixed
  var playerV = 0.0                 // Y velocity of the player
  // Whether the player is dead or not;
  // 0 means alive, >0 is number of frames before respawning
  var dead = 0
  // What frame this is; used to keep track
  // of where the obstacles should be positioned
  var frame = -50
  // List of each obstacle, storing only the Y position of the hole.
  // The X position of the obstacle is calculated by its position in the
  // queue and in the current frame.
  val obstacles = collection.mutable.Queue.empty[Int]
}
