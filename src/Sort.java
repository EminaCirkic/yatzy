/**
 * Created by Emina on 3/1/2017.
 */
public class Sort {


        int array[];

        public Sort(){}

        public Sort(int []oldarray ){
            this.array=oldarray;
            quickStart();
        }

        private void quickStart(){
            quickSort(0,array.length);
        }

        private void quickSort(int low, int high){
            if (low>=high)//inga element kvar, rekursionen avslutas
                return;
            //Ta fram pivotIndex
            int pivotIndex= (low + high)/2;

            //Leta upp exakt rätt plats till värdet som fanns på pivotIndex platsen
            pivotIndex=pivot(low,high,pivotIndex);

            //fortsätt sortera de två vektorerna som den blir uppdelad till
            // om till vänster
            if(low<pivotIndex)
                quickSort(low,pivotIndex);
            //om till höger
            if(pivotIndex<high)
                quickSort(pivotIndex+1,high);
        }


        private int pivot(int start, int stop, int pos){
            //Letar upp exakt rätt plats för det aktuella värdet
            // delar upp vektorn i två delar:
            // en del med värden mindre än eller lika med pivotvärdet
            // en del med högre värden
            // returnerar pivot elementets position

            //Börja med att byta pivoten till startpositionen
            swap(start,pos);

            //Delningsvärden (inte speciellt bra ord ,hitt pångt annat)
            int low=start+1;
            int high=stop;
            while(low<high)
                // de lägre värdena till vänster, då ligger de rätt
                if(array[low]< array[start])
                    low++;
                else// om det finns lägre värden till höger samtidigt som det finns högre värden till vänster, byt plats
                    if(array[--high]< array[start])
                        swap(low,high);
            //lägg sedan tillbaka pivoten på sin plats
            swap(start,--low);
            return low;
        }

        private void swap(int start, int pos){
            int tmp= array[start];
            array[start]= array[pos];
            array[pos]= tmp;
        }



        public int getWinner(){
            return array[array.length-1];}
        }

