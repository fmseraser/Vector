class Vector3D implements java.io.Serializable {

  float x, y, z;

  public Vector3D() {
    this.x = 0;
    this.y = 0;
    this.z = 0;
  }

  public Vector3D(float _x, float _y, float _z) {
    this.x = _x;
    this.y = _y;
    this.z = _z;
  }

  public Vector3D(Vector2D v) {
    this.x = v.x;
    this.y = v.y;
    this.z = 0;
  }

  public Vector3D(Vector3D v) {
    this.x = v.x;
    this.y = v.y;
    this.z = v.z;
  }

  static public Vector3D random() {
    double angle1 = Math.random() * Math.PI * 2;
    double angle2 = Math.random() * Math.PI * 2;
    return new Vector3D(
      (float)(Math.cos(angle1) * Math.cos(angle2)), 
      (float)(Math.sin(angle1) * Math.cos(angle2)), 
      (float)(Math.sin(angle2))
      ).normalize();
  }

  public float mag() {
    return (float) Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
  }

  public float magSq() {
    return (this.x*this.x + this.y*this.y + this.z*this.z);
  }

  public Vector3D add(float _x, float _y, float _z) {
    return new Vector3D(this.x+_x, this.y+_y, this.z+_z);
  }

  public Vector3D add(Vector3D v) {
    return new Vector3D(this.x+v.x, this.y+v.y, this.z+v.z);
  }

  static public Vector3D add(Vector3D v1, Vector3D v2) {
    return new Vector3D(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z);
  }

  public Vector3D sub(float _x, float _y, float _z) {
    return new Vector3D(this.x-_x, this.y-_y, this.z-_z);
  }

  public Vector3D sub(Vector3D v) {
    return new Vector3D(this.x-v.x, this.y-v.y, this.z-v.z);
  }

  static public Vector3D sub(Vector3D v1, Vector3D v2) {
    return new Vector3D(v1.x-v2.x, v1.y-v2.y, v1.z-v2.z);
  }

  public Vector3D mult(float n) {
    return new Vector3D(this.x*n, this.y*n, this.z*n);
  }

  static public Vector3D mult(Vector3D v, float n) {
    return new Vector3D(v.x*n, v.y*n, v.z*n);
  }

  public Vector3D div(float n) {
    return new Vector3D(this.x/n, this.y/n, this.z/n);
  }

  static public Vector3D div(Vector3D v, float n) {
    return new Vector3D(v.x/n, v.y/n, v.z/n);
  }

  public float dist(Vector3D v) {
    float dx = this.x - v.x;
    float dy = this.y - v.y;
    float dz = this.z - v.z;
    return (float) Math.sqrt(dx*dx + dy+dy + dz*dz);
  }

  static public float dist(Vector3D v1, Vector3D v2) {
    float dx = v1.x - v2.x;
    float dy = v1.y - v2.y;
    float dz = v1.z - v2.z;
    return (float) Math.sqrt(dx*dx + dy+dy + dz*dz);
  }

  public float dot(Vector3D v) {
    return this.x*v.x + this.y*v.y + this.z*v.z;
  }

  static public float dot(Vector3D v1, Vector3D v2) {
    return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
  }

  public Vector3D cross(Vector3D v) {
    return new Vector3D(
      this.y*v.z - v.y*this.z, 
      this.z*v.x - v.z*this.x, 
      this.x*v.y - v.x*this.y
      );
  }

  static public Vector3D cross(Vector3D v1, Vector3D v2) {
    return new Vector3D(
      v1.y*v2.z - v2.y*v1.z, 
      v1.z*v2.x - v2.z*v1.x, 
      v1.x*v2.y - v2.x*v1.y
      );
  }

  public Vector3D negative() {
    return new Vector3D(-this.x, -this.y, -this.z);
  }

  public Vector3D normalize() {
    float m = this.mag();
    return (m == 0 || m == 1 ? new Vector3D(this) : this.div(m));
  }

  public Vector3D setMag(float len) {
    return this.normalize().mult(len);
  }

  public Vector3D limit(float max) {
    if (this.magSq() <= max*max) return new Vector3D(this);
    return new Vector3D(this).normalize().mult(max);
  }

  // http://www.cg.info.hiroshima-cu.ac.jp/~miyazaki/knowledge/tech07.html
  public Vector3D rotateX(float theta) {
    return new Vector3D(
      this.x, 
      (float) (this.y*Math.cos(theta) - this.z*Math.sin(theta)), 
      (float) (this.y*Math.sin(theta) + this.z*Math.cos(theta))
      );
  }

  public Vector3D rotateY(float theta) {
    return new Vector3D(
      (float) (this.z*Math.sin(theta) + this.x*Math.cos(theta)), 
      this.y, 
      (float) (this.z*Math.sin(theta) - this.x*Math.sin(theta))
      );
  }

  public Vector3D rotateZ(float theta) {
    return new Vector3D(
      (float) (this.x*Math.cos(theta) - this.y*Math.sin(theta)), 
      (float) (this.x*Math.sin(theta) + this.y*Math.cos(theta)), 
      this.z
      );
  }

  public Vector3D rotate(float alpha, float beta, float gamma) {
    return this.rotateX(alpha).rotateY(beta).rotateZ(gamma);
  }

  public Vector3D rolling(float roll, float pitch, float yaw) {
    float r_sin = (float) Math.sin(roll);
    float r_cos = (float) Math.cos(roll);
    float p_sin = (float) Math.sin(pitch);
    float p_cos = (float) Math.cos(pitch);
    float y_sin = (float) Math.sin(yaw);
    float y_cos = (float) Math.cos(yaw);
    return new Vector3D(
      r_cos*p_cos + r_sin*p_cos - p_sin,
      r_cos*p_sin*y_sin - r_sin*y_cos + r_sin*p_sin*y_sin + r_cos*y_cos + p_cos*y_sin,
      r_cos*p_sin*y_cos + r_sin*y_sin + r_sin*p_sin*y_cos - r_cos*y_sin + p_cos*y_cos
      );
  }

  public Vector3D lerp(Vector3D end, float amt) {
    return new Vector3D(
      this.x + (this.x - end.x) * amt, 
      this.y + (this.y - end.y) * amt, 
      this.z + (this.z - end.z) * amt
      );
  }

  static public Vector3D lerp(Vector3D start, Vector3D end, float amt) {
    return new Vector3D(
      start.x + (start.x - end.x) * amt, 
      start.y + (start.y - end.y) * amt, 
      start.z + (start.z - end.z) * amt
      );
  }

  static public float angleBetween(Vector3D v1, Vector3D v2) {
    if (v1.x == 0 && v1.y == 0 && v1.z == 0 ) return 0.0f;
    if (v2.x == 0 && v2.y == 0 && v2.z == 0 ) return 0.0f;
    double amt = Vector3D.dot(v1, v2) / (v1.mag() * v2.mag());
    if (amt <= -1) {
      return (float) Math.PI;
    } else if (amt >= 1) {
      return 0;
    }
    return (float) Math.acos(amt);
  }

  public Vector3D reflect(Vector3D n) {
    return Vector3D.sub(this, n.mult(2 * Vector3D.dot(this, n)));
  }

  static public Vector3D reflect(Vector3D v, Vector3D n) {
    return Vector3D.sub(v, n.mult(2 * Vector3D.dot(v, n)));
  }

  public Vector3D refract(Vector3D n, float eta) {
    float dot = Vector3D.dot(this, n);
    float d   = 1 - eta*eta * (1 - dot*dot);
    if (0 < d) {
      return Vector3D.sub(
        Vector3D.sub(this, n.mult(dot)).mult(eta), 
        n.mult((float) Math.sqrt(d))
        );
    }
    return this.reflect(n);
  }

  static public Vector3D refract(Vector3D v, Vector3D n, float eta) {
    float dot = Vector3D.dot(v, n);
    float d   = 1 - eta*eta * (1 - dot*dot);
    if (0 < d) {
      return Vector3D.sub(
        Vector3D.sub(v, n.mult(dot)).mult(eta), 
        n.mult((float) Math.sqrt(d))
        );
    }
    return Vector3D.reflect(v, n);
  }

  @Override
    public String toString() {
    return "[ " + this.x + ", " + this.y + ", " + this.z + " ]";
  }

  @Override
    public boolean equals(Object obj) {
    if (!(obj instanceof Vector3D)) {
      return false;
    }
    final Vector3D p = (Vector3D) obj;
    return x == p.x && y == p.y && z == p.z;
  }

  @Override
    public int hashCode() {
    int result = 1;
    result = 31 * result + Float.floatToIntBits(this.x);
    result = 31 * result + Float.floatToIntBits(this.y);
    result = 31 * result + Float.floatToIntBits(this.z);
    return result;
  }
}