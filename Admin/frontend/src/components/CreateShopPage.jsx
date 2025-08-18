import React from "react";
import {Button, Container} from "react-bootstrap";
import {ShopPage} from "./ShopPage";

export const CreateShopPage = () => {
    return(
      <>
          <Container className="min-vh-100 d-flex align-items-center justify-content-center bg-primary">
              <Container className="create-shop text-white bg-dark ">Create Shop</Container>
          </Container>
      </>
    );
}