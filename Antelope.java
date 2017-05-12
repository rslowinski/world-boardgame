import java.util.List;


public class Antelope extends Animal {
    Antelope(MyField[][] fields, List<Organism> organisms) {
        super(fields, Organisms.ANTELOPE.strength, Organisms.ANTELOPE.initiation, Organisms.ANTELOPE.color, Organisms.ANTELOPE.name, organisms);
    }

    Antelope(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, Organisms.ANTELOPE.strength, Organisms.ANTELOPE.initiation, Organisms.ANTELOPE.color, Organisms.ANTELOPE.name, organisms, posX, posY);
    }

    @Override
    int reflectedAttack(Organism opponent) {
        if (generator.nextBoolean()) {
            if (moveToEmptyField()) {
                return 2;
            }
        }
        return super.reflectedAttack(opponent);
    }

    @Override
    void reproduction(Organism parent) {
        MyField emptyFieldForChild = getNearbyEmptyField(parent);
        if (emptyFieldForChild != null && (age >= 0 || parent.age >= 0)) {
            announcements += (name + " reproduces\n");
            Antelope child = new Antelope(fields, organisms, emptyFieldForChild.getX(), emptyFieldForChild.getY());
            organisms.add(child);
        }
    }

    @Override
    public int getRandomMovement() {
        int a = 0;
        while (a == 0)
            a = generator.nextInt(5) - 2; // returns -2/-1/1/2
        return a;
    }

    boolean moveToEmptyField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isInsideBoard(posX + (j - 1), posY + (i - 1)) &&
                        isFieldEmpty(posX + (j - 1), posY + (i - 1))) {
                    emptyField();
                    setOnField(posX + j - 1, posY + i - 1);
                    return true;
                }
            }
        }
        return false;
    }
}