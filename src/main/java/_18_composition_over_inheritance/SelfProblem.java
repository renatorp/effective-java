package _18_composition_over_inheritance;

public class SelfProblem {

	// basic class which we will wrap
	static class Model{
		Controller controller;

		Model(Controller controller){
			this.controller = controller;
			controller.register(this); //Pass SELF reference
		}

		public void makeChange(){
			System.out.println("Making changes");
		}
	}

	static class Controller {
		private Model model;

		public void register(Model model) {
			this.model = model;
		}

		// Here the wrapper just fails to count changes,
		// because it does not know about the wrapped object
		// references leaked
		public void doChanges(){
			model.makeChange();
		}
	}

	// wrapper class
	static class ModelChangesCounter {
		private final Model model;
		private int changesMade;

		ModelChangesCounter(Model model){
			this.model = model;
		}

		// The wrapper is intended to count changes,
		// but those changes which are invoked from
		// Controller are just skipped
		public void makeChange(){
			model.makeChange();
			changesMade++;
		}

		public int getChangesMade() {
			return changesMade;
		}
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		Model wrappedModel = new Model(controller);

		ModelChangesCounter wrapperModel = new ModelChangesCounter(wrappedModel);

		wrapperModel.makeChange();  // +1 change
		controller.doChanges(); // +1 change

		// It should be 2 but is going to be 1
		System.out.println(wrapperModel.getChangesMade());
	}
}
