import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class InvertedPendulumController {

    private FIS fis;

    public InvertedPendulumController(String filename) {
        fis = FIS.load(filename, true);
        if (fis == null) {
            throw new IllegalStateException("Erro ao carregar o arquivo FCL.");
        }
    }

    public double controlAction(double angle, double angularVelocity, double carPosition, double carVelocity) {
        fis.getFuzzyRuleSet().setVariable("angle", angle);
        fis.getFuzzyRuleSet().setVariable("angular_velocity", angularVelocity);
        fis.getFuzzyRuleSet().setVariable("car_position", carPosition);
        fis.getFuzzyRuleSet().setVariable("car_velocity", carVelocity);

        fis.getFuzzyRuleSet().evaluate();

        Variable controlAction = fis.getFuzzyRuleSet().getVariable("control_action");
        return controlAction.getValue();
    }

    public static void main(String[] args) {
        InvertedPendulumController controller = new InvertedPendulumController("inverted_pendulum.fcl");

        double angle = 10; // Exemplo de ângulo em graus
        double angularVelocity = 5; // Exemplo de velocidade angular
        double carPosition = 0.5; // Exemplo de posição do carro
        double carVelocity = -1; // Exemplo de velocidade do carro

        double control = controller.controlAction(angle, angularVelocity, carPosition, carVelocity);
        System.out.println("Ação de controle: " + control);
    }
}