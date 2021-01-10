export type Product = {
    id: number;
    name: string;
    price: number;
    description: string;
    imageUri: string;   
}

export type OrderLocationData = {
    latitude: number;
    longitude: number;
    address: string;
}

type ProductId = {
    id: number;
}

/*Ao adicionar '&' ao final de um type é possível fazer um merge com algum outro type
e ter disponível seus atributos para além dos próprios atributos do type principal*/
export type OrderPayload = {
    products:  ProductId[];  
} & OrderLocationData;