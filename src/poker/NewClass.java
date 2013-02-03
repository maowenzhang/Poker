/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author Jonathan
 */
public class NewClass {
 
    
    CardCollection deck = new CardCollection();
    CardCollection hand1 = new CardCollection();
    CardCollection hand2 = new CardCollection();
    CardCollection discarded = new CardCollection();
    
    deck.shuffle();
    
    
    public class CardCollection{
        int[] cards = new int[9];
    }
    
    public class Hand1{
        
        
    }
}
