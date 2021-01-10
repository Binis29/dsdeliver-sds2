import ProductCard from "./ProductCard";
import { Product } from "./types";
/*uma prop é um parâmetro passado ao component*/
type Props = {
    products: Product[];
}
function ProductsList({ products }: Props) {
    return(
        <div className="orders-list-container">
            <div className="orders-list-items">
                {products.map(product => (
                    <ProductCard key={ product.id } product={product} />))}
            </div>
        </div>
    )
}

export default ProductsList;