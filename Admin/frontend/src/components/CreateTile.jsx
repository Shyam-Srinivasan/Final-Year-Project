import React from "react";
import "./ShopTile.css";

export const CreateTile = ({ openCreate }) => {
  const handleActivate = () => {
    if (typeof openCreate === "function") {
      openCreate();
    }
  };

  const handleKeyDown = (e) => {
    if (e.key === "Enter" || e.key === " ") {
      e.preventDefault();
      handleActivate();
    }
  };

  return (
    <div
      className="shop-card"
      style={{ cursor: "pointer" }}
      onClick={handleActivate}
      onKeyDown={handleKeyDown}
      role="button"
      tabIndex={0}
      aria-label="Create new shop"
    >
      <div className="shop-card__shine" />
      <div className="shop-card__glow" />
      <div className="shop-card__content">
        <div className="shop-card__badge">ADD</div>

        <div className="shop-card__text">
          <p className="shop-card__title">Create Shop</p>
          <p className="shop-card__description">Start a new storefront</p>
        </div>

        <div className="shop-card__footer">
          <div className="shop-card__price">Create</div>
          <div className="shop-card__button" aria-hidden="true">
            <svg height={16} width={16} viewBox="0 0 24 24" aria-hidden="true">
              <path
                strokeWidth={2}
                stroke="currentColor"
                d="M4 12H20M12 4V20"
                fill="currentColor"
              />
            </svg>
          </div>
        </div>
      </div>
    </div>
  );
};