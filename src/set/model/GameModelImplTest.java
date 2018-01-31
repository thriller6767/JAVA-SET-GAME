package set.model;
import java.awt.*;
import java.util.*;
import java.util.List;
import static org.junit.Assert.*;

public class GameModelImplTest {

    @org.junit.Test
    public void test_CanDealThree() {
        System.out.println ("\nTest if the game can have another three cards \n -------------------- ");
        GameModel g = new GameModelImpl();
        assertEquals("The number of cards is ",12, g.getCurrentCards().size());
        assertTrue("Can Deal Three ", g.canDealThree());

    }
    @org.junit.Test
    public void test_CanNotDealThreeCards(){
        System.out.println("\nTest if the game can accept another three cards into game \n ------------------");
        GameModel g = new GameModelImpl();
        g.dealThreeCards();
        assertEquals("The number of cards is ",15, g.getCurrentCards().size());
        assertFalse("Can Deal Three ", g.canDealThree());
    }


    @org.junit.Test
    public void isSet_OneDifference(){
        System.out.println("\n The three cards have different Colors, and are same in any other features \n ---------------");

        GameModel gameModel = new GameModelImpl();
        List<CardModel> cardList = new ArrayList<> (3);

        Color[] colors = new Color[]{Color.red, Color.green, Color.blue};
        CardModel.Shape shape = CardModel.Shape.CIRCLE;
        CardModel.Shade shadeType = CardModel.Shade.OPEN;
        int num = 2;

        for (Color c : colors) {                                // Iterate through the Colors
            CardModel buildCard = new CardModelImpl.Builder()
                        .shape(shape)
                        .shade(shadeType)
                        .shapeNumber(num)
                        .color(c)
                        .build();
                cardList.add(buildCard);
        }
        assertTrue("The given cards from a set",gameModel.isSetParam(cardList.get(0), cardList.get(1), cardList.get(2)));
    }

    @org.junit.Test
    public void isSet_allDifferent(){
        System.out.println("\nThree cards are different in each feature \n ------------------------------");

        GameModel gameModel = new GameModelImpl();

        CardModel a = new CardModelImpl.Builder()
                .shape(CardModel.Shape.SQUARE)
                .shade(CardModel.Shade.OPEN)
                .shapeNumber(1)
                .color(Color.red)
                .build();
        CardModel b = new CardModelImpl.Builder()
                .shape(CardModel.Shape.DIAMOND)
                .shade(CardModel.Shade.SOLID)
                .shapeNumber(2)
                .color(Color.blue)
                .build();
        CardModel c = new CardModelImpl.Builder()
                .shape(CardModel.Shape.CIRCLE)
                .shade(CardModel.Shade.STRIPED)
                .shapeNumber(3)
                .color(Color.GREEN)
                .build();
        assertTrue("Three cards are different in all features, SET", gameModel.isSetParam(a, b, c));
    }

    @org.junit.Test
    public void testSet_NotASet(){
        System.out.println("\nTwo cards are same in shape and shade, another one is different in any features \n ------------------------------");

        GameModel gameModel = new GameModelImpl();
        Color color= Color.red;
        CardModel.Shape shape = CardModel.Shape.CIRCLE;
        List<CardModel> cardList = new ArrayList<> (9);

        for (CardModel.Shade shadeType : CardModel.Shade.values()) {
            for (int i = 1; i <= 3; i++) {
                CardModel buildCard = new CardModelImpl.Builder()
                        .shape(shape)
                        .shade(shadeType)
                        .shapeNumber(i)
                        .color(color)
                        .build();
                cardList.add(buildCard);
            }
        }
        assertFalse("The given cards cannot form a set",gameModel.isSetParam(cardList.get(0), cardList.get(1), cardList.get(8)));
    }

}