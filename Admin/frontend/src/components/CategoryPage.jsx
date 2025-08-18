import {Alert, Col, Container, Row, Spinner} from "react-bootstrap";
import {useCallback, useEffect, useState} from "react";
import axios from "axios";
import {ShopTile} from "./ShopTile";
import {Tile} from "./Tile";


const API_BASE = `http://${window.location.hostname}:8080`;

export const CategoryPage = () => {
    const [college, setCollege] = useState(null);
    const [categories, setCategories] = useState([]);
    const [shop, setShop] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    
    const loadCategories = useCallback( async () => {
        setError("");
        setLoading(true);
        try{
            if(!college?.id){
                setError("No organization found in session. Please Sign in again.");
                return;
            }
            const res = await axios.get(
                `${API_BASE}/categoryList/fetchCategories`,{
                    params: {shopId: shop.id},
                    validateStatus: () => true
                });
            if(res.status === 302 && Array.isArray(res.data)){
                setCategories(res.data);
            } else if(res.data === 204){
                setCategories([]);
            } else if(res.data === 404){
                setError("Organization not found.");
            } else{
                setError(res.data || "Failed to fetch categories.");
            }
        } catch{
            setError("Error fetching categories. Please try again later.");
        } finally {
            setLoading(false);
        }
    }, [shop?.id]);
    
    useEffect(() => {
        try {
            const savedCollege = JSON.parse(localStorage.getItem("college"));
            const savedShop = JSON.parse(localStorage.getItem("shop"));
            setCollege(savedCollege);
            setShop(savedShop);
        } catch {
            setCollege(null);
            setShop(null);
        }
        loadCategories();
    }, [loadCategories]);
    
    return(
        <Container fluid className="bg-white min-vh-100">
            <Row className="mb-3">
                <Col className="p-0">
                    <h4 className="text-center text-white bg-primary m-0 w-100 fs-1 text-uppercase" style={{borderRadius: 0}}>
                        {shop?.name ? `${shop.name} - Shop` : "Shops"}
                    </h4>
                </Col>
            </Row>

            {loading && (
                <Row>
                    <Col className="d-flex align-items-center">
                        <Spinner animation="border" role="status" className="me-2"/>
                        <span>Loading categories...</span>
                                 
                    </Col>
                </Row>
            )}

            {!loading && error && (
                <Row>
                    <Col>
                        <Alert variant="danger">{error}</Alert>
                    </Col>
                </Row>
            )}

            {!loading && !error && (
                <Row className="g-1 justify-content-start">
                    {categories.map((category) => (
                        <Col key={category.category_id ?? category.categoryId} xs="12" sm="6" md="4" lg="3" xl="3">
                            <Tile name={category.category_name ?? category.categoryName} id={category.category_id ?? category.categoryId} onUpdate={loadCategories} type="category"/>
                        </Col>
                    ))}
                </Row>
            )}
            
        </Container>
    );
}