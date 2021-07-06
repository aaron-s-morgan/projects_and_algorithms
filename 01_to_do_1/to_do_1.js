function pushFront(arr, value) {
	for(let i = arr.length; i > 0; i--)
		arr[i] = arr[i-1]
	
	arr[0] = value;
	console.log(arr);
}
function popFront(arr) {
	for(let i = 0; i < arr.length; i++)
		arr[i] = arr[i + 1];
	arr.length = arr.length - 1;
	console.log(arr);
}
function insertAt(arr, index, val){
	for(let i= arr.length; i>index; i--)
	arr[i]= arr[i -1];
	arr[index]=val
	console.log(arr);
}
function removeAt(arr, val){
	if (arr.length>val){
		for(let i = val; i < arr.length; i++)
		arr[i] = arr[i + 1];
		arr.length = arr.length - 1;}
	console.log(arr);
}
function swapPairs(arr){
	for(let i = 0; i< arr.length-1; i+=2){
		let temp = arr[i];
		arr[i] = arr[i+1];
		arr[i+1] = temp;
	}
	console.log(arr);
}

function removeDuplicates(arr){
	let newArr = [];
	for(let i = 0; i<arr.length; i++){
		if(arr[i]==arr[i+1]){
		}
		else{
		newArr.push(arr[i])
		}
	}
	console.log(newArr);
}
pushFront([3,4,5], 5)
popFront([3,4,5])
insertAt([1,2,3,4,5], 1, 15)
removeAt([1,2,3,4,5], 1)
swapPairs([1,2,3,4,5])
removeDuplicates([1,2,3,3,3,3,3,3,4,5,56,56,60])
