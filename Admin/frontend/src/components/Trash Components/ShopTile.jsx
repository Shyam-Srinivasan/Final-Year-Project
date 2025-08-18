import React, { useState } from "react";
import { Modal, Button, Form, Spinner } from "react-bootstrap";
import "../Tile.css";
import axios from "axios";
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";

export const ShopTile = ({ name = "", shop_id ="", onUpdate, onFetchDetails, onSave, password="" }) => {
  const [showModal, setShowModal] = useState(false);
  const [loading, setLoading] = useState(false);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");
  const [form, setForm] = useState({ name, password });
  
  const API_BASE = `http://${window.location.hostname}:8080`;
  const navigate = useNavigate();
  
  const college = (() => {
        try {
            return JSON.parse(localStorage.getItem("college"));
        } catch {
            return null;
        }
    })();
  
  const handleClick = () => {
      localStorage.setItem(
          'shop',
          JSON.stringify(
            {
                id: shop_id,
                name: name
            }
        )
      );
      navigate('/categories');
  }
  
  const handleOpenEdit = async () => {
    setError("");
    setShowModal(true);
    try {
      setLoading(true);
      let details = { name, password: ""};
      if (typeof onFetchDetails === "function") {
        details = await onFetchDetails(shop_id);
      } else if (shop_id) {
        const res = await axios.get(`${API_BASE}/shopList/fetchShopById`, {
          params: { shopId: shop_id }
        });
        
        if (res.ok) {
          details = await res.json();
        }
      }
      setForm({
          name: details?.shopName ?? name,
          password: details?.password ?? "12345",
          collegeId: details?.collegeId ?? "",
        });
    } catch (e) {
      setError("Failed to load shop details.");
      setForm({ name , });
    } finally {
      setLoading(false);
    }
  };

  const handleClose = () => {
    if (!saving) {
      setShowModal(false);
      setError("");
    }
  };

  const handleSave = async () => {
    try {
      setSaving(true);
      setError("");
      if (typeof onSave === "function") {
        await onSave({ id: shop_id, name: form.name, password: form.password });
      } else if (shop_id) {
          const payload = {
              college_id: college.college_id,
              shop_name: form.name,
              password: form.password
          };
          
         console.log("Saving shop with payload:", payload);
          
         const res = await axios.put(
              `${API_BASE}/shopList/updateShop`,
              payload,
              {
                params: { shop_id: shop_id },
                validateStatus: () => true
              }
            );
         
         if(res.status === 200){
             toast.success("ShopPage Updated!", {autoClose: 2000});
             setShowModal(false);
             
             if(typeof onUpdate === "function"){
                 await onUpdate();
             }
         } else {
             toast.error(res.data || "Failed to update shop", {autoClose: 2000});
         }
      }
    } catch (e) {
      setError("Failed to save changes.");
    } finally {
      setSaving(false);
    }
  };

  return (
    <>
      <div className="shop-card"
          style={{ cursor: "pointer" }}
          onClick={handleClick}
          // onKeyDown={}
          role="button"
          tabIndex={0}
          aria-label="Create new shop"
        >
        <div className="shop-card__shine" />
        <div className="shop-card__glow" />
        <div className="shop-card__content">
          <div className="shop-card__image" />

          <div className="shop-card__row">
            <p className="shop-card__title" title={form.name || name}>
              {name}
            </p>

            <button
              type="button"
              className="shop-card__edit"
              aria-label="Edit shop"
              onClick={(e) => {
                e.stopPropagation();
                handleOpenEdit();
              }}
            >
              <svg
                height="16"
                width="16"
                viewBox="0 0 24 24"
                focusable="false"
                aria-hidden="true"
              >
                <path
                  d="M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25z"
                  fill="currentColor"
                />
                <path
                  d="M20.71 7.04a1 1 0 0 0 0-1.41l-2.34-2.34a1 1 0 0 0-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z"
                  fill="currentColor"
                />
              </svg>
            </button>
          </div>
        </div>
      </div>

      <Modal show={showModal} onHide={handleClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Edit Shop</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {loading ? (
            <div className="d-flex align-items-center gap-2">
              <Spinner animation="border" size="sm" />
              <span>Loading…</span>
            </div>
          ) : (
            <>
              {error && (
                <div
                  role="alert"
                  className="alert alert-danger py-2 px-3 mb-3"
                >
                  {error}
                </div>
              )}
              <Form>
                <Form.Group controlId="shopName">
                  <Form.Label>Shop Name</Form.Label>
                  <Form.Control
                    type="text"
                    value={form.name}
                    onChange={(e) => setForm((f) => ({ ...f, name: e.target.value }))}
                    placeholder="Enter shop name"
                    autoFocus
                  />
                </Form.Group>
                  <Form.Group controlId="password">
                      <Form.Label>Password</Form.Label>
                      <Form.Control
                          type="password"
                          value={form.password}
                          onChange={(e) => setForm((f) => ({...f, password: e.target.value}))}
                          placeholder="Enter password"
                          autoFocus
                      />
                  </Form.Group>
              </Form>
            </>
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose} disabled={saving}>
            Cancel
          </Button>
          <Button variant="primary" onClick={handleSave} disabled={saving || loading}>
            {saving ? (
              <>
                <Spinner
                  as="span"
                  animation="border"
                  size="sm"
                  role="status"
                  aria-hidden="true"
                  className="me-2"
                />
                Saving…
              </>
            ) : (
              "Save"
            )}
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};