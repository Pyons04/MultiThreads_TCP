require "fox16"

class MyHello < FXMainWindow
    def initialize(app, title="Hello!")
        main=super(app, title, :width=>323, :height=>200)
        txt_result = FXTextField.new(main, 30)
        #btnHello=FXButton.new(main, "Click and say Hello!", :opts=>BUTTON_NORMAL|LAYOUT_CENTER_X|LAYOUT_CENTER_Y)

        #btnHello.connect(SEL_COMMAND) do |sender, sel, data|
            #msgBox=FXMessageBox.new(btnHello, "Hello from FxRuby", "Hello!", :opts=>MBOX_OK)
            # FXMessageBox.question(main, MBOX_OK, "Hello", "Hello")
            #msgBox.execute
        #end
    end
end
app=FXApp.new
main=MyHello.new(app)
app.create
main.show(PLACEMENT_SCREEN)
app.run