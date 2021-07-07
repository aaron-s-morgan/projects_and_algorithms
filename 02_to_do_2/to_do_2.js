function reverseOrder(arr){
	for(let i = 0; i< arr.length/2; i++){
		let temp = arr[i];
		arr[i] = arr[arr.length-1-i];
		arr[arr.length-1-i] = temp;
	}
	console.log(arr);
}

function shiftBy(arr, val) {
	let t = Math.abs(val);
	while(t > 0) {

		if(val > 0) {
			let temp = arr[arr.length-1]
			
			for(let i = arr.length - 1; i > 0; i--)
				arr[i] = arr[i-1];
			arr[0] = temp;

		} else {
			let temp = arr[0];
			for(let i = 0; i < arr.length - 1; i++) {
				arr[i] = arr[i + 1];
			}
			arr[arr.length-1] = temp;
		}
		t--;
	}
    console.log(arr)
}
function filterRange(arr, min, max){
    for(let i=0; i< arr.length; i++ ){
        if(arr[i] > min && arr[i] < max){
            for(let j = i; j < arr.length; j++){
                arr[j]=arr[j+1];
            }
            arr.length = arr.length-1;
            i--;
        }
    }
    console.log(arr)
}
function concat(arr1, arr2){
    let newArr = [];
	for(let i = 0; i<arr1.length; i++){
		newArr.push(arr1[i])
	}
	for(let j = 0; j<arr2.length; j++){
		newArr.push(arr2[j])
	}
    console.log(newArr);
}


reverseOrder([1,2,3,4,5,6])
shiftBy([1,2,3,4,5,6], -2)
filterRange([1,2,3,4,5,6], 1, 4)
concat([1,2,3,4,5], [2,4,6,8,10])