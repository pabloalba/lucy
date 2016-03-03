package es.seastorm.lucy.screens.play;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dragongames.base.gameobject.SimpleGameObject;
import es.seastorm.lucy.Constants;

public class Platform extends SimpleGameObject{
    boolean mobile;
    boolean win = false;
    public Platform(TextureRegion asset){
        super(asset);
    }

    public void setMobile(boolean mobile){
        this.mobile = mobile;
        if (mobile) {
            this.terminalVelocity.x = Math.round(50 + 100 * Math.random());
            if (Math.random() > 0.5) {
                this.velocity.x = this.terminalVelocity.x;
            } else {
                this.velocity.x = -this.terminalVelocity.x;
            }
        } else {
            this.terminalVelocity.x = 0;
            this.velocity.x = 0;
        }
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (mobile){
            position.x += this.velocity.x * deltaTime;
            if (position.x <= 0){
                position.x =0;
                this.velocity.x = this.terminalVelocity.x;
            }

            if (position.x + dimension.x >= Constants.WIDTH){
                position.x = Constants.WIDTH - dimension.x;
                this.velocity.x = -this.terminalVelocity.x;
            }
        }
    }
}
