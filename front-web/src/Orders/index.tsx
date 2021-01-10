import { useEffect, useState } from 'react';
import { fetchProducts } from '../api';
import OrderLocation from './OrderLocation';
import ProductsList from './ProductsList';
import StepsHeader from './StepsHeader';
import './styles.css';
import { OrderLocationData, Product } from './types';

function Orders (){
    /*products inicializa como uma lista de produtos vazia atribuída
    usando useState([])*/

    const [products, setProducts] = useState<Product[]>([]);
    const [orderLocation, setOrderLocation] = useState<OrderLocationData>();

    useEffect (() => {
        fetchProducts()
            .then(response => setProducts(response.data))
            .catch(error => console.log(error) );
    }, [])

    return (
        <div className="orders-container">
            <StepsHeader />
            <ProductsList products={products} /> 
            <OrderLocation onchangeLocation={location => setOrderLocation(location)}/>
        </div>
    )
}

export default Orders;