class SLLNode {
    constructor(val){
        this.value = val;
        this.next = null;
    }
}

class SLL {
    constructor(){
        this.head = null;
    }
    addNodeToFront(val){
        var newNode = new SLLNode(val);
        if (this.head == null){
            this.head == newNode;
        }
        else{
            newNode.next = this.head;
        }
        this.head = newNode;
    }
    removeNodeFromFront(){
        if (this.head != null){
            this.head = this.head.next;
        }
        else{
            this.head == null;
        }
    }
    frontValue(){
        if (this.head != null){
            return this.head.value;
        }
        else{
            return null;
        }
    }

    
}

var myNode = new SLLNode(10);
console.log(myNode);
var mySLL= new SLL();
mySLL.addNodeToFront(20);
console.log(mySLL);
mySLL.addNodeToFront(10);
console.log(mySLL);
mySLL.addNodeToFront(20);
console.log(mySLL);
mySLL.removeNodeFromFront();
console.log(mySLL);