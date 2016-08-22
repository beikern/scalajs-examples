package example

trait CanvasConfiguration {
  self: JavaScriptContext =>

  /*setup*/
  ctxCanvasState.canvas.width = ctxCanvasState.canvas.parentElement.clientWidth
  ctxCanvasState.canvas.height = 400

  ctxCanvasState.ctx2d.font = "50px sans-serif"
  ctxCanvasState.ctx2d.textAlign = "center"
  ctxCanvasState.ctx2d.textBaseline = "middle"
}
