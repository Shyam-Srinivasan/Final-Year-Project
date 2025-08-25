import React from "react";
import {StatsTile} from "./StatsTile";
import {Container} from "react-bootstrap";

export const HomePage = () => {
    return (
        <Container fluid className="bg-white">
            <div className="stats-row bg-white">
                <StatsTile name="Total Orders"/>
                <StatsTile name="Pending Orders"/>
                <StatsTile name="Completed Orders"/>
                <StatsTile name="Total Orders"/>
            </div>

            <div className="recent-orders" style={{margin: "20px 20px"}}>
                <h3 className="fs-4" style={{color: "black"}}>Recent Orders</h3>
                <div className="recent-order-table">
                    
                </div>
            </div>
        </Container>

    );
};
